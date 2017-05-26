package ecompim.businessLogic;

import Product.*;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by victo on 2017-04-24.
 */
public interface IPIM extends IProductFetcher {

    /**
     * Gets a map of non-detailed products.
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    public HashMap<Integer,Product> fetchProductOverview();


    /**
     * Store the changes in the current product to persistence
     */
    public void saveChanges();

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistence.
     * Overwrites any existing data
     */
    public void collectERPProducts();

    /**
     *
     * @return the product that is being modified
     */
    public DetailedProduct getCurrentProduct();

    /**
     * Fetches a product from persistence and makes it the product currently being modified.
     * If another product was being modified, any changes to that is discarded.
     *
     * @param productID ID of the product to be modified
     */
    public void setCurrentProduct(int productID);

    /**
     * Retrieves the root category from persistence. This is effectively getting the entire DB, since the root cat
     * Knows its children categories, which recursively knows more children cats
     * all of which knows a bunch of products
     * @return
     */
    public Category getRootCategory();

    /**
     * Adds a produt to the category. Does not save it in persistence.
     * @param categoryName
     */
    public void addProductToCategory(String categoryName);

    /**
     *
     * @return The root category, all its children, their children and so on.
     */
    public List<Category> getAllCategories();

    /**
     * Adds new category in the category tree. Does not save it in persistence.
     * @param categoryName
     * @param parent
     */
    public void addNewCategory(String categoryName, String parent); //TODO parent as object or parent as a string?


    /**
     * Returns a set of products in a category, with some limit. Probably at most 50 products.
     * @param categories
     * @return
     */
    public HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories);

    /**
     * Fetch the media path for the current product.
     * @return
     */
    public String fetchThumbnailPathForCurrentProduct();

    /**
     * Clears the PIM cache.
     */
    public int clearCache();


}
