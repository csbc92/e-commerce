package ecompim.businessLogic;

import Product.*;
import ecompim.PIMPersistence.PIMPersistenceFacade;

import java.io.IOException;
import java.util.*;

/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private PIMPersistenceFacade persistence;

    public ProductCatalogue() {
        //persistence = new PIMPersistenceFacade("data/file.dat", "data/category.dat");
        persistence = new PIMPersistenceFacade("jdbc:postgresql://localhost:5432/eComPIM", "postgres", "1234");

    }


    @Override
    public DetailedProduct fetchProduct(int productID) {
        return persistence.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return persistence.searchProducts(searchCriteria);
    }

    @Override
    public IDisplayable fetchMedia(int productID) {
        return null;
    }

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return persistence.fetchProductOverview();
    }

    @Override
    public void saveChanges(DetailedProduct product) {
        persistence.saveProduct(product);
    }

    @Override
    public void saveProductsFromERP(Map<Integer, DetailedProduct> products) {
        persistence.storeProducts(products);
    }


    /**
     * @param categories
     * @return
     */
    @Override
    public HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories) {
        HashMap<Integer, Product> products = new HashMap<>();
        for (Category cat : categories) {

            if (products.size() < 60) {
                products.putAll(persistence.fetchCategoryOverview(cat));
            }

        }
        return products;
    }



    @Override
    public void saveRootCategory(Category rootCategory) {
        persistence.saveRootCategory(rootCategory);
    }

    @Override
    public Category fetchRootCategory() {
        return persistence.getRootCategory();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> returnList = new ArrayList<>();
        Category root = fetchRootCategory();
        returnList.add(root);
        addChildrenCategoriesToList(returnList,root);
    return returnList;
    }

    @Override
    public void addNewCategory(String categoryName, String parent) {
        Category root = fetchRootCategory();
        addNewChildCategory(root,parent,categoryName);
        saveRootCategory(root);
    }

    private void addNewChildCategory(Category parent, String categoryName,String newCategoryName) {
        for(Category cat : parent.getChildren()){
            if(cat.getName().equalsIgnoreCase(categoryName)){
                cat.addChild(new Category(newCategoryName,cat,-1));
            } else {
                addNewChildCategory(cat,categoryName,newCategoryName);
            }
        }
    }

    private void addChildrenCategoriesToList(List<Category> returnList, Category parent) {
        for(Category cat : parent.getChildren()){
            if(cat != null) {
                returnList.add(cat);
                addChildrenCategoriesToList(returnList, cat);
            }
        }
    }

    @Override
    public void addProductToCategory(Product product, String categoryName) {
        Category rootCategory = fetchRootCategory();
        goThroughCategories(rootCategory, categoryName, product);
        saveRootCategory(rootCategory);
    }
    private void goThroughCategories(Category parent, String categoryName, Product product){
        for(Category cat : parent.getChildren()){
            if(!cat.getName().equalsIgnoreCase(categoryName)){
                goThroughCategories(cat,categoryName,product);
            } else {
                cat.addProductID(product.getProductID());
                return;
            }
        }
    }
}
