package ecompim.businessLogic;

import Product.DetailedProduct;
import Product.Product;

import java.util.HashMap;

/**
 * This interface defines the common methods for fetching products.
 */
public interface IProductFetcher {

    /**
     * Fetches a product from persistance
     *
     * @param productID the ID of the detailed product to fetch
     * @return a detailed product
     */
    public DetailedProduct fetchProduct(int productID);

    /**
     * Fetches an HashMap containing any product that matches the search criteria
     *
     * @param searchCriteria part or whole of the product name or description
     * @return an arrayList of non-detailed products
     */
    public HashMap<Integer, Product> searchProducts(String searchCriteria);
}
