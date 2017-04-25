package ecompim.ERPAccess;

import ecompim.Product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class ERPFetcher {

    private Map<Integer, Product> productMap;

    public ERPFetcher() {
        productMap = new HashMap<>();
    }

    public Map<Integer, Product> getProducts() {
        fetchProducts();
        return productMap;
    }

    private void fetchProducts() {
        ERPDummyDB.getProducts();
    }

}
