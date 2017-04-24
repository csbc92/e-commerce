package ecompim.PIMPersistence;

import java.util.HashMap;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMPersistenceFacade {

    private IPIMPersistence ipimPersistence;

    public PIMPersistenceFacade(String filePath) {
        ipimPersistence = new PIMFilePersistence(filePath);
    }

    public PIMPersistenceFacade(String connectionString, String username, String password) {
        ipimPersistence = new PIMDBPersistence(connectionString, username, password);
    }

    public HashMap<int, Product> fetchProductOverview() {
        throw new UnsupportedOperationException();
    }

    public Product fetchProduct(int productID) {
        throw new UnsupportedOperationException();
    }
}
