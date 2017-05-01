package ecompim.ERPAccess;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class ERPFetcher {

    private Map<Integer, DetailedProduct> productMap;

    public ERPFetcher() {
        productMap = new HashMap<>();
    }

    public Map<Integer, DetailedProduct> getProducts() {
        fetchProducts();
        return productMap;
    }

    private void fetchProducts() {
        productMap = ERPDummyDB.getProducts();
    }

}
