/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.server;

import ecompim.businessLogic.IProductFetcher;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author JV
 */
public class Server implements Runnable {

    private ServerTool serverTool;
    private IProductFetcher fetcher;

    public Server(Socket connectionSocket, IProductFetcher fetcher) {
        this.fetcher = fetcher;
        try {
            serverTool = new ServerTool(connectionSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
                while (true) {
                System.out.println("waiting for client");
                String[] clientCmd = serverTool.ReadString().split(":");
                System.out.println("Received: " + Arrays.toString(clientCmd));

                if (clientCmd[0].trim().equalsIgnoreCase("pro")) {
                    int productID = Integer.parseInt(clientCmd[1]);
                    serverTool.sendObj(fetcher.fetchProduct(productID));
                }
            }
        } catch (IOException ex) {
//            Logger.getLogger(ServerTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
