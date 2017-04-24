package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.HashMap;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface IPIMPersistence {

    public HashMap<Integer, Product> fetchProductOverview();

    public DetailedProduct fetchProduct(int productID);

}
