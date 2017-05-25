/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.networking;

import Networking.DetailedProductFrame;
import Networking.StatusCode;
import Product.DetailedProduct;
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
                        DetailedProduct product = fetcher.fetchProduct(productID);

                        //Sending a statuscode a long with the product.
                        StatusCode statusCode;
                        if(product == null) {
                            statusCode = StatusCode.PRODUCTNOTFOUND;
                        } else if(product.getProductID() != productID){
                            statusCode = StatusCode.ERRORINPIM;
                        } else {
                            statusCode = StatusCode.OK;
                        }
                        DetailedProductFrame detailedProductFrame = new DetailedProductFrame(statusCode,product);
                        serverTool.sendObj(detailedProductFrame);

                }
            }
        } catch (IOException ex) {
//            Logger.getLogger(ServerTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
