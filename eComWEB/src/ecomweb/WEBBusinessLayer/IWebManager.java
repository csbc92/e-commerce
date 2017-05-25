package ecomweb.WEBBusinessLayer;


import Product.DetailedProduct;

import java.io.InputStream;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface IWebManager {
    /**
     * Returns a product based on the id that is given as a parameter. If there is an error in getting the product, the method returns null
     * @param productID the id of the product to return
     * @return a DetailedProduct or null if the product cannot be found
     */
    public DetailedProduct getProduct(int productID);

    /**
     * returns the path of the media
     * @param mediaPath
     * @return
     */
    public String getMediaPath(String mediaPath);
}
