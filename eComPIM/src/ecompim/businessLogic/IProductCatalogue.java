package ecompim.businessLogic;

import Product.Category;
import Product.DetailedProduct;
import Product.IDisplayable;
import Product.Product;

import java.io.IOException;
import java.util.*;

public interface IProductCatalogue extends IProductFetcher {

    /**
     * Gets a map of non-detailed products
     *
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    HashMap<Integer, Product> fetchProductOverview();


    /**
     * Store the changes in the current product to persistance
     */
    void saveChanges(DetailedProduct product);

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistence.
     * Overwrites any existing data
     */
    void saveProductsFromERP(Map<Integer, DetailedProduct> products);

    /**
     * Fetch a map of products.
     *
     * @param categories a set of categories, from which products will be found.
     * @return a Hashmap containing products grouped by category.
     */
    HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories);

    /**
     * @param rootCategory
     */
    void saveRootCategory(Category rootCategory);

    /**
     * Obtains the source category of the category tree system.
     *
     * @return The root of the category tree
     */
    Category fetchRootCategory();

    /**
     * Returns a list of contianing all avialable categories.
     *
     * @return a list of categories.
     */
    List<Category> getAllCategories();

    /**
     * Create a new category
     *
     * @param categoryName the name of the category to be created.
     * @param parent       the parent of the category to be created (parent must exist)
     */
    void addNewCategory(String categoryName, String parent);

    /**
     * adds a specific product to a specific category
     *
     * @param product      the product to be added to a category
     * @param categoryName the category to which a product is to be added
     */
    void addProductToCategory(Product product, String categoryName);

    /**
     * Gets the media from a specific product
     *
     * @param productID
     * @return ?
     * @throws IOException
     * @throws ClassNotFoundException
     */
    Set<IDisplayable> fetchMedia(int productID) throws IOException, ClassNotFoundException;

    public Set<IDisplayable> fetchMediaOverview();
}
