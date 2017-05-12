package ecompim.PIMPersistence;

import Product.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMPersistenceFacade implements IPIMPersistenceFacade {

    private IPIMPersistence ipimPersistence;

    public PIMPersistenceFacade(String productFilePath, String categoryFilePath) {
        ipimPersistence = new PIMFilePersistence(productFilePath,categoryFilePath);
    }

    public PIMPersistenceFacade(String connectionString, String username, String password) {
        ipimPersistence = new PIMDBPersistence(connectionString, username, password);
    }
    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
       return ipimPersistence.fetchProductOverview();
    }
    @Override
    public DetailedProduct fetchProduct(int productID) {
        return ipimPersistence.fetchProduct(productID);
    }
    @Override
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        ipimPersistence.storeProducts(products);
    }
    @Override
    public void saveProduct(DetailedProduct product) {
        ipimPersistence.saveProduct(product);
    }
    @Override
    public HashMap<Integer, Product> searchProducts(String value){
        return ipimPersistence.searchProducts(value);
    }

    @Override
    public RootCategory getRootCategory() {
       throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public void saveRootCategory() {
        throw new UnsupportedOperationException(); //TODO
    }


}
