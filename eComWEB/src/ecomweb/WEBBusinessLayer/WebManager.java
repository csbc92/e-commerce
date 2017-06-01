package ecomweb.WEBBusinessLayer;


import Product.DetailedProduct;
import ecomweb.networkclients.ClientHandler;
import network.FTPTool;

/**
 * This class provides implementations of the common interface for the Web system
 */
public class WebManager implements IWebManager {

    private static final String FTPUSER = "b33_20125183";
    private static final String FTPPASS = "ecom1234";
    private static final String FTPHOST = "ftp.byethost33.com";

    private ClientHandler client;
    private FTPTool ftpTool;

    public WebManager() {
        client = new ClientHandler();
        ftpTool = new FTPTool(FTPHOST, FTPUSER, FTPPASS);
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

