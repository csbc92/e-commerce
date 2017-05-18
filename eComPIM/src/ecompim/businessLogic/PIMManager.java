package ecompim.businessLogic;


import ecompim.ERPAccess.ERPFetcher;
import Product.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by victo on 2017-04-24.
 */
public class PIMManager implements IPIM {

    private DetailedProduct currentProduct;
    private IProductCatalogue productCatalogue;
    Thread netHandler;

    /**
     * initializes the PIMManager
     */
    public PIMManager() {
        productCatalogue = new ProductCatalogue();
        collectERPProducts();
        try {
            netHandler = new Thread(new ServerHandler(this,6789 ));
            netHandler.setDaemon(true);
            netHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public Category getRootCategory() {
        return  productCatalogue.fetchRootCategory();
    }

    @Override
    public void addProductToCategory(String categoryName) {
        productCatalogue.addProductToCategory(currentProduct,categoryName);
    }

    @Override
    public List<Category> getAllCategories() {
        return productCatalogue.getAllCategories();
    }

    @Override
    public void addNewCategory(String categoryName, String parent) {
        productCatalogue.addNewCategory(categoryName,parent);
    }

    @Override
    public HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories) {
        return productCatalogue.fetchProductsByCategory(categories);
    }
}
