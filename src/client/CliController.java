package client;

import comm.Receiver;
import comm.Sender;
import domm.GameState;
import java.io.IOException;
import java.net.Socket;

public class CliController {

    private static CliController instance;
    Socket socekt;
    Sender sender;
    Receiver receiver;
    Main gui;
    
    public CliController(Main gui) throws IOException{
        this.gui = gui;
        socekt = new Socket("localhost", 9000);
        sender = new Sender(socekt);
        receiver = new Receiver(socekt);
        new Thread(() -> listenForServer()).start();
    }
    
    static CliController getInstance(Main gui) throws IOException {
        if(instance == null){
            instance = new CliController(gui); // nonstatic variable gui cannot be referenced from a static content
        }
        return instance;
    }
    
    public void sendSlovo(String slovo)throws Exception{
        sender.send(slovo);
    }

    private void listenForServer() {
        try {
            while (true) {                
                Object res = receiver.receive();
                if (res instanceof GameState game) {
                    gui.updateGUI(game);
                    
                    if(game.isIsOver()){
                        if (game.getWinner() != null) {
                            gui.showGameOver("Winner is player: " + game.getWinner());
                        }else{
                            gui.showGameOver("Game over no more tries left!");
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in CliController listenForServer(): " +e.getMessage());
        }
    }
}
