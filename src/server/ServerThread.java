package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread{
    List<String> reci = new ArrayList<>(List.of("avion","kamin","flasa"));
    ServerSocket serverSocket;
    String rec = reci.get((int) (3 * Math.random()));
    List<ClientThread> clients = new ArrayList<>();
    boolean running = true;
    ServerForm form;

    public ServerThread(ServerForm form) {
        this.form = form;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("Odabrana rec: " + rec);
            serverSocket = new ServerSocket(9000);
            
            while (running) {                
                Socket socket = serverSocket.accept();
                
                if(clients.size() >= 2){
                    System.out.println("Server is full!");
                    socket.close();
                    continue;
                }
                
                ClientThread client = new ClientThread(this, socket);
                clients.add(client);
                client.start();
                form.refreshTable(); // na svako dodavanje novog klijenta osvezi tabelu
            }
        } catch (Exception e) {
            System.out.println("Server closed: " + e.getMessage());            
        }
    }
    
    void stopServer(){
        running = false;
        try {
            if(serverSocket != null && !serverSocket.isClosed()){
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("err stopServer: " + e.getMessage());
        }
    }

    void proglasiPobednika(ClientThread client) throws Exception {
        client.gameState.setIsOver(true);
        for (ClientThread cli : clients) {
            cli.sender.send(client.gameState);
        }
        form.refreshTable();
    }

}
