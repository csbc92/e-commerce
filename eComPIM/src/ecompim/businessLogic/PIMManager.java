package ecompim.businessLogic;


import ecompim.ERPAccess.ERPDummyDB;
import ecompim.ERPAccess.ERPFetcher;
import ecompim.Product.*;
import ecompim.PIMPersistence.PIMPersistenceFacade;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by victo on 2017-04-24.
 */
public class PIMManager implements IPIM {

    PIMPersistenceFacade persistance;


    @Override
    public DetailedProduct fetchProduct(int productID) {
        return  persistance.fetchProduct(productID);
    }

    @Override
    public Product[] searchProducts(String searchCriteria) {
       HashMap<Integer,Product> products = persistance.fetchProductOverview();
       ArrayList<Product> toReturn = new ArrayList<>();
        for (Product p: products.values() ) {
            if (String.valueOf(p.getProductID()).equalsIgnoreCase(searchCriteria) || p.getProductName().equalsIgnoreCase(searchCriteria)){ // TODO more criteria support

                toReturn.add(p);
            }
        }

        Product[] returnArray = new Product[toReturn.size()];
        for (int i = 0; i < toReturn.size(); i++) {
            returnArray[i] = toReturn.get(i);
        }
        return returnArray;
    }

    @Override
    public HashMap<Integer, Product> fetchProducts() {
        return persistance.fetchProductOverview();
    }



    @Override
    public void changeProductAttribute(int productID, String attribute, String newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveChanges() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveERPProducts() {
        persistance.storeProducts(new ERPFetcher().getProducts());
    }
}
