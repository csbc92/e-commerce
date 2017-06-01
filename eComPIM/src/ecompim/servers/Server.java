
package ecompim.servers;

import Product.DetailedProduct;
import ecompim.businessLogic.IProductFetcher;
import network.CommandRequest;
import network.CommandResponse;
import network.ServerTool;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class provides the PIM API for network clients.
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
                CommandRequest input = (CommandRequest) serverTool.readObject();
                System.out.println(input);

                if (input.getCommand().trim().equalsIgnoreCase("product")) {
                    Integer productID = (Integer) input.getCommandObject();
                    DetailedProduct detailedProduct = fetcher.fetchProduct(productID);
                    CommandResponse commandResponse = new CommandResponse(0, detailedProduct);
                    serverTool.sendObj(commandResponse);
                }
            }
        } catch (SocketException e) {
            System.out.println("Client closed the connection");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
