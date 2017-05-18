package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by victo on 2017-05-18.
 */
public class ServerTool {
    private Socket connectionSocket;
    private DataInputStream fromClient;
    private DataOutputStream stringToClient;
    private ObjectOutputStream objToClient;

    public ServerTool(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        fromClient = new DataInputStream(connectionSocket.getInputStream());
        stringToClient = new DataOutputStream(connectionSocket.getOutputStream());
        objToClient = new ObjectOutputStream(connectionSocket.getOutputStream());


    }

    public synchronized void sendObj(Object value) throws IOException {
        objToClient.writeObject(value);
    }
    public synchronized void sendString(String value) throws IOException {
        stringToClient.writeUTF(value);
    }

    public synchronized String ReadString() throws IOException {
        String clientInput = fromClient.readUTF();
        return clientInput;
    }
}
