package businessLogic;

/**
 * Created by victo on 2017-04-24.
 */
public interface PIMInterface extends ProductFetcherInterface{

    public Product[] fetchProducts();
    public void changeProductAttribute(int productID, String attribute, String newValue);
    public void saveChanges();

}
