package ecompim.businessLogic;

import ecompim.Product.*;

/**
 * Created by victo on 2017-04-24.
 */
public interface IProductFetcher {

    /**
     * Fetches a product from persistance
     * @param productID the ID of the detailed product to fetch
     * @return a detailed product
     */
    public DetailedProduct fetchProduct(int productID);

    /**
     *
     * Fetches an array containing any product that matches the search criteria
     * @param searchCriteria part or whole of the product name or description
     * @return an array of non-detailed products
     */
    public Product[] searchProducts(String searchCriteria);

}
