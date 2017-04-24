package businessLogic;

/**
 * Created by victo on 2017-04-24.
 */
public interface ProductFetcherInterface {

    public DetailedProduct fetchProduct(int productID);
    public Product[] searchProducts(String searchCriteria);

}
