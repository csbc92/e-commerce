package ecompim.server;

import ecompim.businessLogic.IProductFetcher;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by victo on 2017-05-18.
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
        System.out.println("The server is running.");
       try {
           try {
               while (true) {
                   new Thread(new Server(listener.accept(), fetcher)).start();
               }
           } finally {
               listener.close();
           }
       }catch (IOException ex){
           ex.printStackTrace();
       }
    }
}
