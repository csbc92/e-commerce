package businessLogic;

import java.util.ArrayList;

/**
 * Created by victo on 2017-04-24.
 */
public class PIMController implements PIMInterface {

    PIMPersistanceFacade persistance;


    @Override
    public DetailedProduct fetchProduct(int productID) {
        return persistance.fetchProduct(productID);
    }

    @Override
    public Product[] searchProducts(String searchCriteria) {
       Product[] products = persistance.fetchProductOverview();
       ArrayList<Product> toReturn = new ArrayList<Product>();
        for (Product p: products ) {
            if (String.valueOf(p.productID).equalsIgnoreCase(searchCriteria) || p.name.equalsIgnoreCase(searchCriteria)){ // TODO more criteria support

                toReturn.add(p);
            }
        }
        return toReturn.toArray();
    }

    @Override
    public Product[] fetchProducts() {
        return persistance.fetchProductOverview();
    }

    @Override
    public void changeProductAttribute(int productID, String attribute, String newValue) {
        
    }

    @Override
    public void saveChanges() {

    }
}
