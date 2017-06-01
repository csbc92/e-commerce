
package ecomweb.networkclients;

import Product.DetailedProduct;
import network.ClientTool;
import network.CommandRequest;
import network.CommandResponse;

import java.io.IOException;

/**
 * This class is responsible for communicating with the PIM API.
 */
public class ClientHandler {

    private ClientTool client;
    private final String pimServer = "localhost";
    private final int pimPort = 6789;

    public ClientHandler() {
        client = new ClientTool(pimServer, pimPort);
    }

    /**
     * Returns a product given the products id, if not found it returns null
     *
     * @param productId
     * @return A detailedproduct if it is found in PIM, otherwise it returns null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public DetailedProduct getProduct(int productId) {
        DetailedProduct detailedProduct = null;
        try {
            CommandRequest commandRequest = new CommandRequest("product", new Integer(productId));
            client.sendObject(commandRequest);

            CommandResponse commandResponse = (CommandResponse) client.readObj();

            if (commandResponse != null) {
                System.out.println(commandResponse.getResponseMessage());
                if (commandResponse.getResponseCode() == 0) {
                    detailedProduct = (DetailedProduct) commandResponse.getResponseObject();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return detailedProduct;
    }

}
