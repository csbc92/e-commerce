/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.network;

import java.io.*;
import java.net.*;

public class ClientTool {


    public Socket clientSocket;
    private DataOutputStream toServer;
    private DataInputStream stringFromServer;
    private ObjectInputStream objFromServer;
    private String IP;
    private int port;


    /**
     * A client tool to socket communication.
     * @param IP The IP-address of the server
     * @param port The communication port
     * @throws IOException
     */
    public ClientTool(String IP, int port) {
        this.IP = IP;
        this.port = port;
        connect();
    }

    private synchronized Boolean connect(){
        try {
            if (clientSocket == null || clientSocket.isClosed()) {
                clientSocket = new Socket(this.IP, this.port);
                toServer = new DataOutputStream(clientSocket.getOutputStream());
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
     * Send a given string to the server
     * @param value The String to be send
     * @throws IOException
     */
    public synchronized void sendString(String value) throws IOException {

        if (connect()) {
            toServer.writeUTF(value);
        }
    }

    /**
     * Read a given string send from the server
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
