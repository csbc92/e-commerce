package ecompim.networkclients;

import Product.IDisplayable;
import network.ClientTool;
import network.CommandRequest;
import network.CommandResponse;

import java.io.IOException;
import java.util.Set;

/**
 * This class is responsible for communicating with the DAM API.
 */
public class ClientHandler {

    private ClientTool clientTool;
    private final String damServer = "localhost";
    private final int damPort = 5678;

    public ClientHandler() {
        clientTool = new ClientTool(damServer, damPort);
    }

    /**
     * Fethes media from DAM based on the given media IDs
     *
     * @param mediaIDs A set of Media IDs
     * @return Returns a Set of IDisplayable
     */
    public Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs) {

        Set<IDisplayable> result = null;

        // Ask DAM (via network) for paths for each media.
        try {
            // Send command
            CommandRequest commandRequest = new CommandRequest("mediapaths", mediaIDs);
            clientTool.sendObject(commandRequest);

            // Receive command
            CommandResponse commandResponse = (CommandResponse) clientTool.readObj();
            if (commandResponse != null) {
                System.out.println(commandResponse.getResponseMessage());

                if (commandResponse.getResponseCode() == 0) {
                    result = (Set<IDisplayable>) commandResponse.getResponseObject();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Fetches a media overview from DAM.
     *
     * @return Returns a Set of IDisplayables
     */
    public Set<IDisplayable> fetchMediaOverview() {

        Set<IDisplayable> result = null;

        // Ask DAM (via network) for paths for each media.
        try {
            // Send command
            CommandRequest commandRequest = new CommandRequest("overview", null);
            clientTool.sendObject(commandRequest);

            // Receive command
            CommandResponse commandResponse = (CommandResponse) clientTool.readObj();
            if (commandResponse != null) {
                System.out.println(commandResponse.getResponseMessage());

                if (commandResponse.getResponseCode() == 0) {
                    result = (Set<IDisplayable>) commandResponse.getResponseObject();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
