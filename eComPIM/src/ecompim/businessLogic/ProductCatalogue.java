package ecompim.businessLogic;

import Product.*;
import ecompim.PIMPersistence.PIMPersistenceFacade;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private PIMPersistenceFacade persistance;
    private HashSet<Category> testCat = new HashSet<Category>();

    public ProductCatalogue() {
        persistance = new PIMPersistenceFacade("data/file.dat", "data/category.dat");
        //      testCategories();
    }
    //public void testCategories() {
      //  rootCategory = new Category("Produkter");

//        rootCategory.addChild(new Category("TV"));
  //      rootCategory.addChild(new Category("Hvidevarer"));
    //    Category cat = new Category("Computer dele");
      //  rootCategory.addChild(cat);
        //Category cat2 = new Category("CPU", cat);
        //cat.addChild(cat2);
        //Category cat3 = new Category("CASE", cat);
        //cat.addChild(cat3);
        //cat.addChild(new Category("Grafikkort", cat));
        //cat.addChild(new Category("Motherboard", cat));

//        cat2.addProductID(7);
//        cat2.addProductID(8);
//        cat2.addProductID(9);

//        cat3.addProductID(3);
//        cat3.addProductID(6);

  //      testCat.add(cat2);
    //    testCat.add(cat3);
    //}

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return persistence.fetchProduct(productID);
    }


    public DetailedProduct fetchProductNet(int productID) {
        return persistence.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return persistence.searchProducts(searchCriteria);
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
        HashMap<Integer, Product> productList = new HashMap<>();
        for (Category cat : categories) {
            for (Integer id : cat.getProductIDSet()) {
                productList.put(id, fetchProduct(id));
            }
        }
        return productList;
    }



    @Override
    public void saveRootCategory(Category rootCategory) {
        persistance.saveRootCategory(rootCategory);
    }

    @Override
    public Category fetchRootCategory() {
        return persistance.getRootCategory();
    }

    @Override
    public void addProductToCategory(Product product, String categoryName) {
        throw new UnsupportedOperationException(); //TODO
    }


}
