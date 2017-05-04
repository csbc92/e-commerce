package ecompim.businessLogic;

import ecompim.Product.*;

import java.util.ArrayList;
import java.util.HashMap;

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
     * Fetches an arrayList containing any product that matches the search criteria
     * @param searchCriteria part or whole of the product name or description
     * @return an arrayList of non-detailed products
     */
    public HashMap<Integer, Product> searchProducts(String searchCriteria);

}
