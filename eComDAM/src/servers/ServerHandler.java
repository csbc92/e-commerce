package servers;

import businesslogic.IMediaFetcher;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class is resposible for handling clients that connects to the server.
 */
public class ServerHandler implements Runnable {
    private IMediaFetcher fetcher;
    private ServerSocket listener;

    public ServerHandler(IMediaFetcher f, int port) throws IOException {
        fetcher = f;
        listener = new ServerSocket(port);

    }

    /**
     * Handles all clients trying to connect to the server.
     * A new thread is instantiated when a new client connects to the server.
     */
    @Override
    public void run() {
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
