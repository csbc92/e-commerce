package server;

import businesslogic.IMediaFetcher;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by victo on 2017-05-18.
 */
public class ServerHandler implements Runnable {
    private IMediaFetcher fetcher;
    private ServerSocket listener;

    public ServerHandler(IMediaFetcher f, int port) throws IOException {
        fetcher = f;
        listener = new ServerSocket(port);

    }
    @Override
    public void run() {
        System.out.println("The server is running.");
        try {
            try {
                while (true) {
                  Thread t =  new Thread(new Server(listener.accept(), fetcher));
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
