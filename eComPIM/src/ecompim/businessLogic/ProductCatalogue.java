package ecompim.businessLogic;

import Product.*;
import ecompim.ERPAccess.ERPFetcher;
import ecompim.PIMPersistence.PIMPersistenceFacade;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private PIMPersistenceFacade persistance;

    public ProductCatalogue() {
        persistance = new PIMPersistenceFacade("data/file.dat","data/category.dat");
    }
    @Override
    public DetailedProduct fetchProduct(int productID) {
        return  persistance.fetchProduct(productID);
    }


    public DetailedProduct fetchProductNet(int productID) {
        return  persistance.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {return persistance.searchProducts(searchCriteria);}

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return persistance.fetchProductOverview();
    }

    @Override
    public void saveChanges(DetailedProduct product) {
        persistance.saveProduct(product);
    }

    @Override
    public void saveProductsFromERP(Map<Integer, DetailedProduct> products) {
        persistance.storeProducts(products);
    }

    @Override
    public HashMap<Integer, Product> fetchProductsByCategory(String categoryName) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public RootCategory getRootCategory() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public void saveRootCategory() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public void fetchRootCategory() {

    }

    @Override
    public void addProductToCategory(Product product, String categoryName) {
        throw new UnsupportedOperationException(); //TODO
    }


}
