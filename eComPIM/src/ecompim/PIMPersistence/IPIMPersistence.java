package ecompim.PIMPersistence;

import ecompim.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vedsted on 24-04-2017.
 */
public interface IPIMPersistence {

    public HashMap<Integer, Product> fetchProductOverview();

    public Product fetchProduct(int productID);

}
