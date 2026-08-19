package comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class Receiver implements Serializable{
    Socket socket;
    ObjectInputStream in;

    public Receiver(Socket socket) throws IOException{
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    public Object receive()throws Exception{
        return in.readObject();
    }
}
