package ecompim.businessLogic;


import ecompim.ERPAccess.ERPFetcher;
import Product.*;
import javafx.scene.control.TreeItem;

import java.util.HashMap;

/**
 * Created by victo on 2017-04-24.
 */
public class PIMManager implements IPIM {

    private DetailedProduct currentProduct;
    private ProductCatalogue productCatalogue;
    Thread netHandler;

    /**
     * initializes the PIMManager
     */
    public PIMManager() {
        productCatalogue = new ProductCatalogue();
        collectERPProducts();
        netHandler = new Thread(new NetHandler(this));
        netHandler.setDaemon(true);
        netHandler.start();
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return  productCatalogue.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {return productCatalogue.searchProducts(searchCriteria);}

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return productCatalogue.fetchProductOverview();
    }

    @Override
    public void saveChanges() {
        productCatalogue.saveChanges(currentProduct);
    }

    @Override
    public void collectERPProducts() {
       productCatalogue.saveProductsFromERP(new ERPFetcher().getProducts());
    }

    @Override
    public DetailedProduct getCurrentProduct() {
        return this.currentProduct;
    }

    @Override
    public void setCurrentProduct(int productID) {
        currentProduct = fetchProduct(productID);
    }

    @Override
    public TreeItem<String> categoryOverview() {
        return  productCatalogue.categoryOverview();
    }
}
