package networking;

import businesslogic.IMediaFetcher;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerHandler implements Runnable {
    private IMediaFetcher fetcher;
    private ServerSocket listener;

    public ServerHandler(IMediaFetcher f, int port) throws IOException {
        fetcher = f;
        listener = new ServerSocket(port);

    }

    @Override
    public void run() {
        System.out.println("The networking is running.");
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
