/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.networking;

import Product.DetailedProduct;
import ecompim.businessLogic.IProductFetcher;
import network.ServerTool;

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
                String[] clientCmd = serverTool.readString().split(":");
                System.out.println("Received: " + Arrays.toString(clientCmd));

                if (clientCmd[0].trim().equalsIgnoreCase("pro")) {
                    int productID = Integer.parseInt(clientCmd[1]);
                    if(fetcher.fetchProduct(productID) != null) {
                        serverTool.sendObj(fetcher.fetchProduct(productID));
                    } else {
                        serverTool.sendObj(new DetailedProduct(-1,"error",0,"error",0,0));
                    }
                }
            }
        } catch (IOException ex) {
//            Logger.getLogger(ServerTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
