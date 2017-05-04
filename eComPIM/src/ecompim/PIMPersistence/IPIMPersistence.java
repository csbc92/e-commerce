package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface IPIMPersistence {

    /**
     * Gets a map of non-detailed products
     * @return A map with Product IDs as key and non-detailed products as values.
     */
    public HashMap<Integer, Product> fetchProductOverview();

    /**
     * Fetches a product from persistance
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
     * @param product the product that is to be saved.
     */
    public void saveProduct(DetailedProduct product);

    //TODO: Tilføj dette til klassediagrammerne.
    public HashMap<Integer, Product> searchProducts(String value);

}
