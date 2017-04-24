package businessLogic;

import ecompim.Product.*;

/**
 * Created by victo on 2017-04-24.
 */
public interface IProductFetcher {

    public DetailedProduct fetchProduct(int productID);
    public Product[] searchProducts(String searchCriteria);

}
