package ecomweb.WEBBusinessLayer;


import Product.DetailedProduct;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface IWebManager {
    /**
     * Returns a product based on the id that is given as a parameter
     * @param productID the id of the product to return
     * @return a DetailedProduct
     */
    public DetailedProduct getProduct(int productID);
}
