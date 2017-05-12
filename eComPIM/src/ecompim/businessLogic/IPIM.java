package ecompim.businessLogic;

import Product.*;

import java.util.HashMap;

/**
 * Created by victo on 2017-04-24.
 */
public interface IPIM extends IProductFetcher {

    /**
     * Gets a map of non-detailed products
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    public HashMap<Integer,Product> fetchProductOverview();


    /**
     * Store the changes in the current product to persistance
     */
    public void saveChanges();

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistance.
     * Overwrites any existing data
     */
    public void saveERPProducts();

    /**
     *
     * @return the product that is being modified
     */
    public DetailedProduct getCurrentProduct();

    /**
     * Fetches a product from persistance and makes it the product currently being modified.
     * If another product was being modified, any changes to that is discarded.
     *
     * @param productID ID of the product to be modified
     */
    public void setCurrentProduct(int productID);
}
