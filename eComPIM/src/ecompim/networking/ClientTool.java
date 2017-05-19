package ecompim.networking;

/**
 * Created by victo on 2017-05-18.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import java.io.*;
import java.net.*;

public class ClientTool {


    private Socket clientSocket;
    private DataOutputStream toServer;
    private DataInputStream stringFromServer;
    private ObjectInputStream objFromServer;


    public ClientTool(String ip,int port) throws IOException {
        System.out.println("Client");
        clientSocket = new Socket(ip, port);
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
