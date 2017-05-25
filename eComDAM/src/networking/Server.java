package networking;

import Networking.DisplayableFrame;
import Networking.StatusCode;
import Product.IDisplayable;
import businesslogic.IMediaFetcher;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by victo on 2017-05-18.
 */
public class Server extends Thread {
    private ServerTool tool;
    private Socket socket;
    private IMediaFetcher fetcher;

    public Server(Socket socket, IMediaFetcher f) {
        this.socket = socket;
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
                IDisplayable displayable = fetcher.fetchMedia(input);
                StatusCode statusCode;
                if(displayable == null) {
                    statusCode = StatusCode.PRODUCTNOTFOUND;
                } else if(displayable.getID() != Integer.parseInt(input)){
                    statusCode = StatusCode.ERRORINDAM;
                } else {
                    statusCode = StatusCode.OK;
                }
                DisplayableFrame displayableFrame = new DisplayableFrame(statusCode,displayable);
                tool.sendObj(displayableFrame);
                //socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
