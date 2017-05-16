package ecompim.businessLogic;

import Product.*;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by danie on 12-05-2017.
 */
public interface IProductCatalogue extends IProductFetcher {

    /**
     * Gets a map of non-detailed products
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    HashMap<Integer,Product> fetchProductOverview();


    /**
     * Store the changes in the current product to persistance
     */
    void saveChanges(DetailedProduct product);

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistance.
     * Overwrites any existing data
     */
    void saveProductsFromERP(Map<Integer, DetailedProduct> products);

    HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories);


    void saveRootCategory(Category rootCategory);

    Category fetchRootCategory();

    void addProductToCategory(Product product,String categoryName);

    CheckBoxTreeItem<Category> categoryOverview();

    CheckBoxTreeItem<String> searchCategory(String value);

}
