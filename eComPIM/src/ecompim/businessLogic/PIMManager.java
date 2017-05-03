package ecompim.businessLogic;


import ecompim.ERPAccess.ERPFetcher;
import ecompim.Product.*;
import ecompim.PIMPersistence.PIMPersistenceFacade;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by victo on 2017-04-24.
 */
public class PIMManager implements IPIM {

    private PIMPersistenceFacade persistance;
    private DetailedProduct currentProduct;

    public PIMManager() {
        persistance = new PIMPersistenceFacade("data/file.dat");
        //saveERPProducts();
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return  persistance.fetchProduct(productID);
    }

    @Override
    public Product[] searchProducts(String searchCriteria) {
       HashMap<Integer,Product> products = persistance.fetchProductOverview();
       ArrayList<Product> toReturn = new ArrayList<>();
        for (Product p: products.values() ) {
            if (String.valueOf(p.getProductID()).equalsIgnoreCase(searchCriteria) || p.getName().equalsIgnoreCase(searchCriteria)){ // TODO more criteria support

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
    public HashMap<Integer, Product> fetchProductOverview() {
        return persistance.fetchProductOverview();
    }



    @Override
    public void changeProductAttribute(int productID, String attribute, String newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveChanges() {
        persistance.saveProduct(currentProduct);
    }

    @Override
    public void saveERPProducts() {
        persistance.storeProducts(new ERPFetcher().getProducts());
    }

    @Override
    public DetailedProduct getCurrentProduct() {
        return this.currentProduct;
    }

    @Override
    public void setCurrentProduct(int productID) {
        currentProduct = fetchProduct(productID);
    }
}
