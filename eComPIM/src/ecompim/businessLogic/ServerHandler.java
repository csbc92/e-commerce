/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.businessLogic;

import Product.Product;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JV
 */
public class ServerHandler implements Runnable {

    private Server server;
    private IProductFetcher fetcher;

    public ServerHandler(IProductFetcher fetcher) {
        this.fetcher = fetcher;
        server = new Server();
    }

    @Override
    public void run() {
        try {
            server.createSever(6789);

            while (true) {
                System.out.println("waiting for client");
                String[] clientCmd = server.ReadString().split(":");
                System.out.println("Received: " + clientCmd);

                if (clientCmd[0].trim().equalsIgnoreCase("pro")) {
                    int productID = Integer.parseInt(clientCmd[1]);
                    server.sendObj(fetcher.fetchProduct(productID));
                }
            }
        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
