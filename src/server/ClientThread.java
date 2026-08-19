package server;

import comm.Receiver;
import comm.Sender;
import domm.GameState;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class ClientThread extends Thread {
    ServerThread server;
    Socket socket;
    Sender sender;
    Receiver receiver;
    
    GameState gameState;
    boolean isOver;
    
    public ClientThread(ServerThread server, Socket socket) throws IOException{
        this.server = server;
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        // koriscenaSlova, preostali pokusaji, revealed _ _ _ _ _
        gameState = new GameState(
                new ArrayList<>(), 
                6, 
                "_".repeat(server.rec.length())
        );
        gameState.setPlayerName(String.valueOf(server.clients.size() + 1));
    }
    
    @Override
    public void run(){
        try {
            while (!isOver && gameState.getPreostaliPokusaji() > 0) {                
                Object res = receiver.receive();
                
                if(res instanceof String slovo){
                    System.out.println("Pokusaj igraca: " + slovo);
                    validate(slovo);
                }
            }
        } catch (Exception e) {
            System.out.println("Err in ClientThread run: " + e.getMessage());
        }
    }

    private boolean validate(String unos) throws Exception {
        if(unos == null || unos.length() != 1)
            throw new Exception("Mozete poslati samo jedno slovo");
        
        char slovo = unos.charAt(0);
        boolean pogodio = false;
        StringBuilder revealed = new StringBuilder(gameState.getRevealed());
        
        for (int i = 0; i < server.rec.length(); i++) {
            if(server.rec.charAt(i) == slovo){
                revealed.setCharAt(i, slovo);
                pogodio = true;
            }
        }
        gameState.setRevealed(revealed.toString());
        gameState.getKoriscenaSlova().add(unos);
        
        if (!pogodio) gameState.setPreostaliPokusaji(gameState.getPreostaliPokusaji() - 1);
        
        checkGameOver();
        
        if (isOver) {
            if(gameState.getWinner() != null){
                server.proglasiPobednika(this); // kraj igre za sve samo ako neko pogodi rec
            }else{
                sendState(); // samo ovaj igrac je izgubio, drugi nastavljaju da igraju
                server.form.refreshTable();
            }
            
        }else{
            sendState();
            server.form.refreshTable(); // osveži tabelu i posle "običnog" poteza
        }
        return pogodio;
    }

    private void checkGameOver() {
        if(gameState.getRevealed().equals(server.rec)){
            isOver = true;
            gameState.setWinner(gameState.getPlayerName());
        }else if(gameState.getPreostaliPokusaji() <= 0){
            isOver = true; // kraj bez pobednika
        }
        gameState.setIsOver(isOver);
    }

    private void sendState() {
        try {
            sender.send(gameState);
        } catch (Exception e) {
            System.out.println("Error in sendState: " + e.getMessage());
        }
    }
}
