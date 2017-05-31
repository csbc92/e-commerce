package ecompim.PIMPersistence;

import Product.Category;
import Product.DetailedProduct;
import Product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface IPIMPersistence {

    /**
     * Gets a map of non-detailed products
     *
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    public HashMap<Integer, Product> fetchProductOverview();

    /**
     * Fetches a product from persistance
     *
     * @param productID the ID of the detailed product to fetch
     * @return a detailed product
     */
    public DetailedProduct fetchProduct(int productID);

    /**
     * Saves products to persistance
     * Any existing products will be discarded and/or overwritten
     *
     * @param products the products to save
     */
    public void storeProducts(Map<Integer, DetailedProduct> products);

    /**
     * Adds a product to the products saved in persistance. i.e. Saves a product in persistance.
     *
     * @param product the product that is to be saved.
     */
    public void saveProduct(DetailedProduct product);

    /**
     * Searches for a product, based on the parameter given.
     *
     * @param value the value to search for
     * @return a map of the products that match the search value, with Integers as keys and Products as values
     */
    public HashMap<Integer, Product> searchProducts(String value);

    /**
     * Gets overview of products from a given category
     *
     * @param category the category to look for
     * @return a map of product from the category chosen
     */
    public HashMap<Integer, Product> getCategoryOverview(Category category);

    /**
     * saves the given category as the root of the category tree
     *
     * @param rootCategory Category object to be saved as root.
     */
    public void saveRootCategory(Category rootCategory);

    /**
     * Gets the root of the category tree.
     *
     * @return category object that is the root.
     */
    public Category fetchRootCategory();

    /**
     * Gets a set of media IDs associated with the given product
     *
     * @param productID
     * @return
     */
    public Set<Integer> getMediaIDs(int productID);

    /**
     * Returns the highest category id
     * @return the highest category id
     */
    public int getMaxCatId();
}
