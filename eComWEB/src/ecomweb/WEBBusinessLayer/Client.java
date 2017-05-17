/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.WEBBusinessLayer;

import java.io.*;
import java.net.*;

public class Client {


    private Socket clientSocket;
    private DataOutputStream toServer;
    private DataInputStream stringFromServer;
    private ObjectInputStream objFromServer;


    public void createClient(int port) throws IOException {
        System.out.println("Client");
        clientSocket = new Socket("localhost", port);
        toServer = new DataOutputStream(clientSocket.getOutputStream());
        objFromServer = new ObjectInputStream(clientSocket.getInputStream());
        stringFromServer = new DataInputStream(clientSocket.getInputStream());

    }

    public synchronized void sendString(String value) throws IOException {
        toServer.writeUTF(value);
    }

    public synchronized String readString() throws IOException {
        String clientInput = stringFromServer.readUTF();
        return clientInput;
    }

    public synchronized Object readObj() throws ClassNotFoundException, IOException {
        Object clientInput = objFromServer.readObject();
        return clientInput;
    }
}
