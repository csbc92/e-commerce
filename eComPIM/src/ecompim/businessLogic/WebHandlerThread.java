package ecompim.businessLogic;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.io.*;
import java.net.Socket;

/**
 * Created by victo on 2017-05-07.
 */
public class WebHandlerThread implements Runnable {
    private Socket socket;
    private int clientNumber;
    private IProductFetcher fetcher;

    public WebHandlerThread(IProductFetcher fetcher, Socket socket, int clientNumber) {
        this.socket = socket;
        this.fetcher= fetcher;
        this.clientNumber = clientNumber;
        log("New connection with client# " + clientNumber + " at " + socket);
    }

    /**
     * Services this thread's client by first sending the
     * client a welcome message then repeatedly reading strings
     * and sending back the capitalized version of the string.
     */
    public void run() {
        try {

            // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Send a welcome message to the client.
            out.println("Hello, you are client #" + clientNumber + ".");
          // out.println("Enter a line with only a period to quit\n");

            // Get messages from the client, line by line; return them
            // capitalized
            while (true) {
                System.out.println("reading input");
                String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
                System.out.println("input = " + input);
                System.out.println("fetching product");
                DetailedProduct product = fetcher.fetchProduct(Integer.parseInt(input));
                System.out.println("sending name");
                out.println(product.getName());
                System.out.println("sending product");
                oos.writeObject(product);
                System.out.println("product sent");
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }

    /**
     * Logs a simple message.  In this case we just write the
     * message to the server applications standard output.
     */
    private void log(String message) {
        System.out.println(message);
    }
}
