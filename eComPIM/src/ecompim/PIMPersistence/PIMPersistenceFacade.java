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
       return ipimPersistence.fetchProductOverview();
    }

    public DetailedProduct fetchProduct(int productID) {
        return ipimPersistence.fetchProduct(productID);
    }

    public void storeProducts(Map<Integer, Product> products) {
        ipimPersistence.storeProducts(products);
    }
}
