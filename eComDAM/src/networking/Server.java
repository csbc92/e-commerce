package networking;

import Product.IDisplayable;
import businesslogic.IMediaFetcher;

import network.*;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;

/**
 * Created by victo on 2017-05-18.
 */
public class Server extends Thread {
    private ServerTool serverTool;
    private Socket socket;
    private IMediaFetcher mediaFetcher;

    public Server(Socket socket, IMediaFetcher f) {
        this.socket = socket;
        this.mediaFetcher = f;
        try {
            serverTool = new ServerTool(socket);
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
                CommandRequest input = (CommandRequest)serverTool.readObject();

                System.out.println(input);

                if (input.getCommand().trim().equalsIgnoreCase("overview")) {
                    Set<IDisplayable> displayables = mediaFetcher.fetchMediaOverview();
                    CommandResponse response = new CommandResponse(0, displayables);

                    if (displayables.size() == 0) {
                        response.setResponseMessage("No displayables media found.");
                    }

                    serverTool.sendObj(response);
                } else if (input.getCommand().trim().equalsIgnoreCase("mediapaths")) {
                    Set<Integer> mediaIDs = (Set<Integer>)input.getCommandObject();
                    Set<IDisplayable> displayables = mediaFetcher.fetchMedia(mediaIDs);
                    CommandResponse response = new CommandResponse(0, displayables);

                    if (displayables.size() == 0) {
                        response.setResponseMessage("No displayables media found.");
                    }

                    serverTool.sendObj(response);
                }
                //socket.close();
            }
        } catch (SocketException e) {
            System.out.println("Client closed the connection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
