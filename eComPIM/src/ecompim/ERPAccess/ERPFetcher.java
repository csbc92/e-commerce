package ecompim.ERPAccess;

import Product.DetailedProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for fetching products from the external ERP system.
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
     *
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
