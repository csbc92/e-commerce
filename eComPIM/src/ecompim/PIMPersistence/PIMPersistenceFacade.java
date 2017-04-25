package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.HashMap;
import java.util.Map;

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

    public HashMap<Integer, Product> fetchProductOverview() {
        throw new UnsupportedOperationException();
    }

    public DetailedProduct fetchProduct(int productID) {
        throw new UnsupportedOperationException();
    }

    public void storeProducts(Map<Integer, Product> products) {
        ipimPersistence.storeProducts(products);
    }
}
