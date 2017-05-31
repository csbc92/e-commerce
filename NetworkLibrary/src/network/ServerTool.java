package network;

import java.io.*;
import java.net.Socket;

public class ServerTool {
    private Socket connectionSocket;
    private DataInputStream primitiveFromClient;
    private ObjectInputStream objectFromClient;
    private DataOutputStream stringToClient;
    private ObjectOutputStream objToClient;

    /**
     * A server tool to socket communication.
     *
     * @param connectionSocket The socket connection
     * @throws IOException
     */
    public ServerTool(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        primitiveFromClient = new DataInputStream(connectionSocket.getInputStream());
        objectFromClient = new ObjectInputStream((connectionSocket.getInputStream()));
        stringToClient = new DataOutputStream(connectionSocket.getOutputStream());
        objToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
    }

    /**
     * Send a given object the client
     *
     * @param value The object to be send.
     * @throws IOException
     */
    public synchronized void sendObj(Object value) throws IOException {
        objToClient.writeObject(value);
    }

    /**
     * Send a given string to the client
     *
     * @param value The String to be send
     * @throws IOException
     */
    public synchronized void sendString(String value) throws IOException {
        stringToClient.writeUTF(value);
    }

    /**
     * Read a given String from the client
     *
     * @return The string send from the client
     * @throws IOException
     */
    public synchronized String readString() throws IOException {
        String clientInput = primitiveFromClient.readUTF();
        return clientInput;
    }

    /**
     * Reads an object from the input stream.
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized Object readObject() throws IOException, ClassNotFoundException {
        Object object = objectFromClient.readObject();
        return object;
    }
}
