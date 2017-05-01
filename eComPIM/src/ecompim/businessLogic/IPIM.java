package ecompim.businessLogic;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.HashMap;

/**
 * Created by victo on 2017-04-24.
 */
public interface IPIM extends IProductFetcher {

    public HashMap<Integer,Product> fetchProductOverview();
    public void changeProductAttribute(int productID, String attribute, String newValue);
    public void saveChanges(DetailedProduct product);

    public void saveERPProducts();
}
