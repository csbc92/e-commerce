package ecompim.businessLogic;

import Product.*;
import ecompim.ERPAccess.ERPFetcher;
import ecompim.PIMPersistence.PIMPersistenceFacade;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private PIMPersistenceFacade persistance;
    private Category rootCategory;
    private HashSet<Category> testCat = new HashSet<Category>();

    public ProductCatalogue() {
        persistance = new PIMPersistenceFacade("data/file.dat", "data/category.dat");
        testCategories();
    }

    public void testCategories() {
        rootCategory = new Category("Produkter");

        rootCategory.addChild(new Category("TV"));
        rootCategory.addChild(new Category("Hvidevarer"));
        Category cat = new Category("Computer dele");
        rootCategory.addChild(cat);
        Category cat2 = new Category("CPU", cat);
        cat.addChild(cat2);
        Category cat3 = new Category("CASE", cat);
        cat.addChild(cat3);
        cat.addChild(new Category("Grafikkort", cat));
        cat.addChild(new Category("Motherboard", cat));

        cat2.addProductID(7);
        cat2.addProductID(8);
        cat2.addProductID(9);

        cat3.addProductID(3);
        cat3.addProductID(6);

        testCat.add(cat2);
        testCat.add(cat3);
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return persistance.fetchProduct(productID);
    }


    public DetailedProduct fetchProductNet(int productID) {
        return persistance.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return persistance.searchProducts(searchCriteria);
    }

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


    /**
     * @param categories
     * @return
     */
    @Override
    public HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories) {
        if (categories.isEmpty()) {
            return fetchProductOverview();
        }
        HashMap<Integer, Product> productList = new HashMap<>();
        for (Category cat : categories) {
            for (Integer id : cat.getProductIDSet()) {
                productList.put(id, fetchProduct(id));
            }
        }
        return productList;
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

    /**
     * Get category overview
     *
     * @return
     */
    @Override
    public CheckBoxTreeItem<Category> categoryOverview() {
        CheckBoxTreeItem<Category> root = new CheckBoxTreeItem<>(rootCategory);
        root.setExpanded(true);
        for (Category cat : rootCategory.getChildren()) {
            root.getChildren().add(getCategories(cat));
        }
        return root;
    }

    @Override
    public CheckBoxTreeItem<String> searchCategory(String value) {
        throw new UnsupportedOperationException(); //TODO
    }

    private CheckBoxTreeItem<Category> getCategories(Category category) {
        CheckBoxTreeItem<Category> item = new CheckBoxTreeItem<>(category);
        for (Category cat : category.getChildren()) {
            item.getChildren().add(getCategories(cat));
        }
        return item;
    }


}
