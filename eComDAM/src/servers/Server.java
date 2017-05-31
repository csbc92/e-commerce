package servers;

import Product.IDisplayable;
import businesslogic.IMediaFetcher;
import network.CommandRequest;
import network.CommandResponse;
import network.ServerTool;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;

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
     * Listening for a CommandRequest from the client connected to the server, and response with a CommandResponse
     * based on the received request.
     */
    public void run() {
        try {

            System.out.println("A Client is connected to the Digital Asset Management System servers");

            while (true) {
                CommandRequest input = (CommandRequest) serverTool.readObject();

                System.out.println(input);

                if (input.getCommand().trim().equalsIgnoreCase("overview")) {
                    Set<IDisplayable> displayables = mediaFetcher.fetchMediaOverview();
                    CommandResponse response = new CommandResponse(0, displayables);

                    if (displayables.size() == 0) {
                        response.setResponseMessage("No displayables media found.");
                    }

                    serverTool.sendObj(response);
                } else if (input.getCommand().trim().equalsIgnoreCase("mediapaths")) {
                    Set<Integer> mediaIDs = (Set<Integer>) input.getCommandObject();
                    Set<IDisplayable> displayables = mediaFetcher.fetchMedia(mediaIDs);
                    CommandResponse response = new CommandResponse(0, displayables);

                    if (displayables.size() == 0) {
                        response.setResponseMessage("No displayables media found.");
                    }

                    serverTool.sendObj(response);
                }
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
