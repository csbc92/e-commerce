/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.network;


import Product.DetailedProduct;
import network.ClientTool;
import network.CommandRequest;
import network.CommandResponse;

import java.io.IOException;

/**
 * @author JV
 */
public class ClientHandler {

    private ClientTool client;

    public ClientHandler() {
            client = new ClientTool("localhost",6789);
    }

    /**
     *  Returns a product given the products id, if not found it returns null
     * @param productId
     * @return A detailedproduct if it is found in PIM, otherwise it returns null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public DetailedProduct getProduct(int productId){
        DetailedProduct detailedProduct = null;
        try {
        CommandRequest commandRequest = new CommandRequest("product", new Integer(productId));
        client.sendObject(commandRequest);


        CommandResponse commandResponse = (CommandResponse)client.readObj();

            if (commandResponse != null) {
                System.out.println(commandResponse.getResponseMessage());
                if (commandResponse.getResponseCode() == 0) {
                    detailedProduct = (DetailedProduct)commandResponse.getResponseObject();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
       return detailedProduct;
    }

}
