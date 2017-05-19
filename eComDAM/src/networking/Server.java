package networking;

import businesslogic.IMediaFetcher;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by victo on 2017-05-18.
 */
public class Server extends Thread {
    private ServerTool tool;
    private IMediaFetcher fetcher;

    public Server(Socket socket, IMediaFetcher f) {
        fetcher = f;
        try {
            tool = new ServerTool(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Services this thread's client by first sending the
     * client a welcome message then repeatedly reading strings
     * and sending back the capitalized version of the string.
     */
    public void run() {
        try {

            System.out.println("A Client is connected to the Digital Asset Management System networking");

            while (true) {
                String input = tool.ReadString();
                System.out.println(input);
                if (input == null || input.equals(".")) {
                    break;
                }
                //TODO something with the input from client
                Image a =fetcher.fetchMedia(input).getMedia();
               tool.sendObj(a);
            }
        } catch (IOException e) {

        }
    }

}
