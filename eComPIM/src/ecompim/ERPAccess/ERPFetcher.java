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

    /**
     * Initializes the ERPFetcher
     */
    public ERPFetcher() {
        productMap = new HashMap<>();
    }

    /**
     * Fetches products from the ERP
     * @return a map with Integers as key and DetailedProducts as values
     */
    public Map<Integer, DetailedProduct> getProducts() {
        fetchProducts();
        return productMap;
    }

    /**
     * Fetches products from the ERPDummyDB class.
     */
    private void fetchProducts() {
        productMap = ERPDummyDB.getProducts();
    }

}
