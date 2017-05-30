package ecompim.businessLogic;

import Product.*;
import ecompim.PIMPersistence.IPIMPersistenceFacade;
import ecompim.PIMPersistence.PIMPersistenceFacade;
import ecompim.networkclients.ClientHandler;
import network.*;

import java.io.IOException;
import java.util.*;



/**
 * Created by JV on 12-05-2017.
 */
public class ProductCatalogue implements IProductCatalogue {

    private IPIMPersistenceFacade persistence;
    private ClientTool cTool;

    /**
     * Creates a new instance of ProductCatalogue with a connection string.
     */
    public ProductCatalogue() {
        //persistence = new PIMPersistenceFacade("data/file.dat", "data/category.dat");
        persistence = new PIMPersistenceFacade("jdbc:postgresql://localhost:5432/eComPIM", "postgres", "1234");
        cTool = new ClientTool("localhost", 5678);
    }

    /**
     * Fetches a datiled product with the given ID
     * @param productID the ID of the detailed product to fetch
     * @return Returns an instance of detailed product with the given ID.
     */
    @Override
    public DetailedProduct fetchProduct(int productID) {

        DetailedProduct product = persistence.fetchProduct(productID);

        if (product != null) {
            Set<IDisplayable> media = this.fetchMedia(product.getProductID());
            if (media != null) {
                for (IDisplayable displayable : media) {
                    product.addMedia(displayable);
                }
            }
        }

        return product;
    }

    /**
     * Returns a map containing the products matching the search.
     * @param searchCriteria part or whole of the product name or description
     * @return HashMap object containing products that match the search.
     */
    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return persistence.searchProducts(searchCriteria);
    }


    /**
     * Fetch all media for the specified product ID.
     * @param productID
     * @return Returns a Set of IDisplayables if the product has any media associated. Otherwise null is returned.
     */
    @Override
    public Set<IDisplayable> fetchMedia(int productID) {
        // Fetch the products media id's from the PIM DB.
        Set<Integer> mediaIDs = persistence.getMediaIDs(productID);
        Set<IDisplayable> result = null;

        // Ask DAM for associated media
        ClientHandler clientHandler = new ClientHandler();
        result = clientHandler.fetchMedia(mediaIDs);

        return result;
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        // Fetch the products media id's from the PIM DB.
        Set<IDisplayable> result = null;

        // Ask DAM for associated media
        ClientHandler clientHandler = new ClientHandler();
        result = clientHandler.fetchMediaOverview();

        return result;
    }


    /**
     * Collects a map of simple products.
     * @return a hashmap object containing simple products.
     */
    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return persistence.fetchProductOverview();
    }


    /**
     * Permanently saves the changes made to the specified product.
     * @param product The Product object whose changes are to be saved.
     */
    @Override
    public void saveChanges(DetailedProduct product) {
        persistence.saveProduct(product);
    }


    /**
     * Loads and creates instances of Product from the ERP.
     * @param products Populates the given map with products.
     */
    @Override
    public void saveProductsFromERP(Map<Integer, DetailedProduct> products) {
        persistence.storeProducts(products);
    }

    /**
     * Fills a hashmap with products up to a limit
     * @param categories A set of the available categories.
     * @return an instance of HashMap containing a limited number or products grouped by category.
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


    /**
     * Saves the given category as the root of the category tree.
     * @param rootCategory An instance of category to be used as root.
     */
    @Override
    public void saveRootCategory(Category rootCategory) {
        persistence.saveRootCategory(rootCategory);
    }


    /**
     * Gets the root of the category tree.
     * @return an instance of Category, that is the root.
     */
    @Override
    public Category fetchRootCategory() {
        return persistence.getRootCategory();
    }

    /**
     * Returns all available categories.
     * @return an instance of List containing all Categories, with root as the first.
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> returnList = new ArrayList<>();
        Category root = fetchRootCategory();
        returnList.add(root);
        addChildrenCategoriesToList(returnList,root);
    return returnList;
    }

    /**
     * Creates a new cateogry with the given name and parent.
     * @param categoryName the name of the category to be created.
     * @param parent the parent of the category to be created (parent must exist)
     */
    @Override
    public void addNewCategory(String categoryName, String parent) {
        Category root = fetchRootCategory();
        addNewChildCategory(root,parent,categoryName);
        saveRootCategory(root);
    }

    /**
     * Assigns a category to an existing parent.
     * @param parent The parent being assigned a new child.
     * @param categoryName The name of the category being assigned to a new parent.
     * @param newCategoryName The name of the category being created.
     */
    private void addNewChildCategory(Category parent, String categoryName,String newCategoryName) {
        for(Category cat : parent.getChildren()){
            if(cat.getName().equalsIgnoreCase(categoryName)){
                int max = persistence.getMaxCatId();
                cat.addChild(new Category(newCategoryName,cat,max+1)); // one above the maximum must be a unique id
            } else {
                addNewChildCategory(cat,categoryName,newCategoryName);
            }
        }
    }


    /**
     * Adds the children of a category to the list object parameter.
     * @param returnList the list being populated
     * @param parent the parent from which children should be derived.
     */
    private void addChildrenCategoriesToList(List<Category> returnList, Category parent) {
        for(Category cat : parent.getChildren()){
            if(cat != null) {
                returnList.add(cat);
                addChildrenCategoriesToList(returnList, cat);
            }
        }
    }


    /**
     * Adds a product to the specified category
     * @param product the product to be added to a category
     * @param categoryName the category to which a product is to be added
     */
    @Override
    public void addProductToCategory(Product product, String categoryName) {
        Category rootCategory = fetchRootCategory();
        goThroughCategories(rootCategory, categoryName, product);
        saveRootCategory(rootCategory);
    }

    /**
     * Searches for a category to add a product id.
     * @param parent the parent of the categories to be searched initially.
     * @param categoryName the category to be searched for.
     * @param product the product to be added to a category.
     */
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

