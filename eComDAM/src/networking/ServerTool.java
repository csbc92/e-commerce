package networking;

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

    /**
     * A server tool to socket communication.
     * @param connectionSocket The socket connection
     * @throws IOException
     */
    public ServerTool(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        fromClient = new DataInputStream(connectionSocket.getInputStream());
        stringToClient = new DataOutputStream(connectionSocket.getOutputStream());
        objToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
    }

    /**
     * Send a given object the client
     * @param value The object to be send.
     * @throws IOException
     */
    public synchronized void sendObj(Object value) throws IOException {
        objToClient.writeObject(value);
    }

    /**
     * Send a given string to the client
     * @param value The String to be send
     * @throws IOException
     */
    public synchronized void sendString(String value) throws IOException {
        stringToClient.writeUTF(value);
    }

    /**
     * Read a given String from the client
     * @return The string send from the client
     * @throws IOException
     */
    public synchronized String ReadString() throws IOException {
        String clientInput = fromClient.readUTF();
        return clientInput;
    }
}
