package comm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Sender implements Serializable{
    Socket socket;
    ObjectOutputStream out;

    public Sender(Socket socket) throws IOException{
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public void send(Object data)throws Exception{
        out.writeObject(data);
        out.flush();
        out.reset();
    }
}
