package ecomweb.WEBBusinessLayer;



import ecompim.Product.DetailedProduct;
import ecompim.businessLogic.IProductFetcher;
import ecompim.businessLogic.PIMManager;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class WebManager implements IWebManager{
   // private IProductFetcher productFetcher;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectInputStream ois;

    public WebManager() {
       // productFetcher = new PIMManager();


        try {
            this.connectToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public DetailedProduct getProduct(int productID){

        out.println("1");
        try {
            sleep(1);
            String productName = in.readLine();
           sleep(1);
            System.out.println(productName);
            return (DetailedProduct) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return  null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
       }


    }

    public void connectToServer() throws IOException {

        // Get the server address from a dialog box.
        String serverAddress = "localhost";

        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, 9898);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        ois = new ObjectInputStream(socket.getInputStream());
        // Consume the initial welcoming messages from the server

        System.out.println(in.readLine());

    }
//    @Override
//    public DetailedProduct getProduct(int productID) {
//     return productFetcher.fetchProduct(productID);
//  }

}
