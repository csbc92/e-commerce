package ecompim.ERPAccess;

import java.util.HashMap;

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

    }

}
