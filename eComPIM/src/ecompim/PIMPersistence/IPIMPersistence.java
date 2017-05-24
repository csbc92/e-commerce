package ecompim.PIMPersistence;

import Product.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface  IPIMPersistence {

    /**
     * Gets a map of non-detailed products
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    HashMap<Integer, Product> fetchProductOverview();

    /**
     * Fetches a product from persistance
     * @param productID the ID of the detailed product to fetch
     * @return a detailed product
     */
    DetailedProduct fetchProduct(int productID);

    /**
     * Saves products to persistance
     * Any existing products will be discarded and/or overwritten
     *
     * @param products the products to save
     */
    void storeProducts(Map<Integer, DetailedProduct> products);

    /**
     * Adds a product to the products saved in persistance. i.e. Saves a product in persistance.
     * @param product the product that is to be saved.
     */
    void saveProduct(DetailedProduct product);

    /**
     * Searches for a product, based on the parameter given.
     * @param value the value to search for
     * @return a map of the products that match the search value, with Integers as keys and Products as values
     */
    HashMap<Integer, Product> searchProducts(String value);

    /**
     * Gets overview of products from a given category
     * @param category the category to look for
     * @return a map of product from the category chosen
     */
    HashMap<Integer, Product> getCategoryOverview(Category category);

    /**
     * saves the given category as the root of the category tree
     * @param rootCategory Category object to be saved as root.
     */
    void saveRootCategory(Category rootCategory);

    /**
     * Gets the root of the category tree.
     * @return category object that is the root.
     */
    Category fetchRootCategory();

}
