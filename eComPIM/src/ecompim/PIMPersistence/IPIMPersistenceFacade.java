package ecompim.PIMPersistence;

import Product.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by danie on 12-05-2017.
 */
public interface IPIMPersistenceFacade{

        /**
         * Gets a map of simple products.
         * @return HashMap containging instances of Product. 
         */
        public HashMap<Integer, Product> fetchProductOverview() ;

        /**
         * Collects a DetailedProduct based on the specified ID
         * @param productID ID of the product to be collected.
         * @return The chosen product as instance of DetailedProduct.
         */
        public DetailedProduct fetchProduct(int productID);

        /**
         * Stores the given map of products.
         * @param products Map of products to be stores.
         */
        public void storeProducts(Map<Integer, DetailedProduct> products) ;

        /**
         * Saves the specified product
         * @param product Product to be saved.
         */
        public void saveProduct(DetailedProduct product);

        /**
         * Search list of products for a String Value
         * @param value String to be searched for.
         * @return Map of products matching the search String
         */
        public HashMap<Integer, Product> searchProducts(String value);

        /**
         * Fetches a map of simple products from a specified category.
         * @param category The category to be searched
         * @return a HashMap of the found products.
         */
        HashMap<Integer, Product> fetchCategoryOverview(Category category);

        /**
         * returns the root of the category tree
         * @return instance of category that is the root.
         */
        public Category getRootCategory();

        /**
         * saves a category as the root.
         * @param rootCategory
         */
        public void saveRootCategory(Category rootCategory);

        /**
         * Gets a set of media IDs associated with the given product
         * @param productID
         * @return
         */
        Set<Integer> getMediaIDs(int productID);
}
