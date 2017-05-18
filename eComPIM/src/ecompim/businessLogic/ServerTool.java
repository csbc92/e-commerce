/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.businessLogic;

import java.io.*;
import java.net.*;

public class ServerTool {



    private Socket connectionSocket;
    private DataInputStream fromClient;
    private DataOutputStream stringToClient;
    private ObjectOutputStream objToClient;

    public ServerTool(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        System.out.println("ServerTool");
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
