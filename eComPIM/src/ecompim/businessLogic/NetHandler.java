package ecompim.businessLogic;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by victo on 2017-05-05.
 */
public class NetHandler implements Runnable {
    IProductFetcher fetcher;
    public NetHandler(IProductFetcher f){
         fetcher = f;
    }
    @Override
    public void run() {
        System.out.println("The product fetching server is running.");
        int clientNumber = 0;
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(9898);

        try {
            while (true) {
                new WebHandlerThread(fetcher, listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
