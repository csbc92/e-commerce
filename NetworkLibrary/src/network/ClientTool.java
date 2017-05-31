package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTool {

    public Socket clientSocket;
    private ObjectOutputStream objectToServer;
    private DataInputStream stringFromServer;
    private ObjectInputStream objFromServer;
    private String IP;
    private int port;


    /**
     * A client tool to socket communication.
     *
     * @param IP   The IP-address of the server
     * @param port The communication port
     * @throws IOException
     */
    public ClientTool(String IP, int port) {
        this.IP = IP;
        this.port = port;
        connect();
    }

    /**
     * Connect to the server with the IP-address and port specified in the constructor
     *
     * @return True if the client is connected to the server otherwise false.
     */
    private synchronized Boolean connect() {
        try {
            if (clientSocket == null || clientSocket.isClosed()) {
                clientSocket = new Socket(this.IP, this.port);
                objectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                objFromServer = new ObjectInputStream(clientSocket.getInputStream());
                stringFromServer = new DataInputStream(clientSocket.getInputStream());
            } else {
            }
            return clientSocket.isConnected();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Send a given object to the server
     *
     * @param object The object to be send
     * @throws IOException
     */
    public synchronized void sendObject(Object object) throws IOException {
        if (connect()) {
            objectToServer.writeObject(object);
        }
    }

    /**
     * Read a given string send from the server
     *
     * @return
     * @throws IOException
     */
    public synchronized String readString() throws IOException {
        if (connect()) {
            String clientInput = stringFromServer.readUTF();
            return clientInput;
        }
        return null;
    }

    /**
     * Read a given object send from the server
     *
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public synchronized Object readObj() throws ClassNotFoundException, IOException {
        if (connect()) {
            Object clientInput = objFromServer.readObject();
            return clientInput;
        }
        return null;
    }
}
