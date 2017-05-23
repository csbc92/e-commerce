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
    private Category rootCategory;

    public ProductCatalogue() {
        //persistence = new PIMPersistenceFacade("data/file.dat", "data/category.dat");
        persistence = new PIMPersistenceFacade("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
        rootCategory = fetchRootCategory();
        //    testCategories(); //For creating a standard.
    }
// public void testCategories() {
//    HashSet<Category> testCat = new HashSet<Category>();
//    Category rootCategory = new Category("Produkter");
//    rootCategory.addChild(new Category("TV"));
//    rootCategory.addChild(new Category("Hvidevarer"));
//    Category cat = new Category("Computer dele");
//    rootCategory.addChild(cat);
//    Category cat2 = new Category("CPU", cat);
//    cat.addChild(cat2);
//    Category cat3 = new Category("CASE", cat);
//    cat.addChild(cat3);
//    cat.addChild(new Category("Grafikkort", cat));
//    cat.addChild(new Category("Motherboard", cat));
//    cat2.addProductID(7);
//    cat2.addProductID(8);
//    cat2.addProductID(9);
//    cat3.addProductID(3);
//    cat3.addProductID(6);
//    testCat.add(cat2);
//    testCat.add(cat3);
//    saveRootCategory(rootCategory);
//}

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return persistence.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return persistence.searchProducts(searchCriteria);
    }

    @Override
    public IDisplayable fetchMedia(int productID) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return persistence.fetchProductOverview();
    }

    @Override
    public void saveChanges(DetailedProduct product) {
        persistence.saveProduct(product);
        saveRootCategory(rootCategory);
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
        returnList.add(rootCategory);
        addChildrenCategoriesToList(returnList,rootCategory);
        return returnList;
    }

    @Override
    public void addNewCategory(String categoryName, String parent) {

        addNewChildCategory(rootCategory,parent,categoryName);

    }

    private void addNewChildCategory(Category parent, String categoryName,String newCategoryName) {
        for(Category cat : parent.getChildren()){
            if(cat.getName().equalsIgnoreCase(categoryName)){
                cat.addChild(new Category(newCategoryName,cat,301));
            } else {
                addNewChildCategory(cat,categoryName,newCategoryName);
            }
        }
        //System.out.println("We don did add a cat");
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
        goThroughCategories(rootCategory, categoryName, product);
//        saveRootCategory(rootCategory);

    }
    private void goThroughCategories(Category parent, String categoryName, Product product){
        for(Category cat : parent.getChildren()){
            if(!cat.getName().equalsIgnoreCase(categoryName)){
                goThroughCategories(cat,categoryName,product);
            } else {
                cat.addProductID(product.getProductID());
               // System.out.println("IT IS ADDED");
                return;
            }
        }
    }
}
