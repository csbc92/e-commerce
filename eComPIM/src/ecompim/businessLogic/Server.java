/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.businessLogic;

import java.io.*;
import java.net.*;

public class Server {

    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private DataInputStream fromClient;
    private DataOutputStream stringToClient;
    private ObjectOutputStream objToClient;

    public synchronized void createSever(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server");
        connectionSocket = serverSocket.accept();
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
