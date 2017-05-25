/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.network;


import Networking.DetailedProductFrame;
import Product.DetailedProduct;

import java.io.IOException;

/**
 * @author JV
 */
public class ClientHandler {

    private ClientTool client;

    public ClientHandler() {
            client = new ClientTool("localhost",6789);
    }

    public DetailedProductFrame getProduct( int productId) throws IOException, ClassNotFoundException {
        client.sendString("pro:" + productId);
        DetailedProductFrame detailedProductFrame = (DetailedProductFrame) client.readObj();
        return detailedProductFrame;
    }

}
