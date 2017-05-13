package ecompim.businessLogic;

import Product.*;
import ecompim.ERPAccess.ERPFetcher;
import ecompim.PIMPersistence.PIMPersistenceFacade;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private PIMPersistenceFacade persistance;
    private RootCategory rootCategory;

    public ProductCatalogue() {
        persistance = new PIMPersistenceFacade("data/file.dat","data/category.dat");
        testCategories();
    }

    public void testCategories(){
        rootCategory = new RootCategory("Produkter");

        rootCategory.addChild(new Category("TV"));
        rootCategory.addChild(new Category("Hvidevarer"));
        Category cat = new Category("Computer dele");
        rootCategory.addChild(cat);
        Category cat2 = new Category("RAM",cat);
        cat.addChild(cat2);
        cat2.addChild(new Category("Corsair"));
        cat.addChild(new Category("CPU",cat));
        cat.addChild(new Category("Grafikkort",cat));
        cat.addChild(new Category("Motherboard",cat));
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

    @Override
    public CheckBoxTreeItem<String> categoryOverview() {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>(rootCategory.getName());
        root.setExpanded(true);
        for (Category cat : rootCategory.getChildren() ) {
            root.getChildren().add(getCategories(cat));

        }
        return root;
    }

    @Override
    public TreeItem<String> searchCategory(String value) {
        throw new UnsupportedOperationException(); //TODO
    }

    private TreeItem<String> getCategories(Category category){
        CheckBoxTreeItem<String> item = new CheckBoxTreeItem<>(category.getName());
        for (Category cat : category.getChildren()) {
            item.getChildren().add(getCategories(cat));
        }
        return item;
    }


}
