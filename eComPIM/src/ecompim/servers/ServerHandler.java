package ecompim.servers;

import ecompim.businessLogic.IProductFetcher;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class is resposible for handling clients that connects to the server.
 */
public class ServerHandler implements Runnable {
    private IProductFetcher fetcher;
    private ServerSocket listener;

    public ServerHandler(IProductFetcher f, int port) throws IOException {
        fetcher = f;
        listener = new ServerSocket(port);

    }
    @Override
    public void run() {
        System.out.println("The servers is running.");
       try {
           try {
               while (true) {
                   Thread t = new Thread(new Server(listener.accept(), fetcher));
                   t.setDaemon(true);
                   t.start();
               }
           } finally {
               listener.close();
           }
       }catch (IOException ex){
           ex.printStackTrace();
       }
    }
}
