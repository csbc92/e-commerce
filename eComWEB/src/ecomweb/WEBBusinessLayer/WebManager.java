package ecomweb.WEBBusinessLayer;


import Product.DetailedProduct;
import ecomweb.network.ClientHandler;
import network.FTPTool;



/**
 * Created by Vedsted on 24-04-2017.
 */

public class WebManager implements IWebManager {

    private ClientHandler client;
    private FTPTool ftpTool;

    public WebManager() {

        client = new ClientHandler();
        ftpTool = new FTPTool(FTPTool.username, FTPTool.password);
    }


    @Override
    public DetailedProduct getProduct(int productID) {
        DetailedProduct detailedProduct = client.getProduct(productID);
        return detailedProduct;
    }

    @Override
    public String getMediaPath(String mediaPath) {
        System.out.println("manager path: " + mediaPath);
        return ftpTool.retrieveFile(mediaPath.trim());
    }


}

