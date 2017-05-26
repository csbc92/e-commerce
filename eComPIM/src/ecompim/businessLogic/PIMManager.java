package ecompim.businessLogic;

import ecompim.ERPAccess.ERPFetcher;
import Product.*;
import network.ClientTool;
import ecompim.networking.ServerHandler;
import network.FTPTool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by victo on 2017-04-24.
 */

public class PIMManager implements IPIM {

    private DetailedProduct currentProduct;
    private IProductCatalogue productCatalogue;
    private Thread netHandler;
    private ClientTool cTool;

    /**
     * initializes the PIMManager
     */

    public PIMManager() {

        productCatalogue = new ProductCatalogue();

        try {
            netHandler = new Thread(new ServerHandler(this, 6789));
            netHandler.setDaemon(true);
            netHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

           cTool = new ClientTool("localhost", 5678);
            //  cTool.sendString("Bob's your auntie");

    }

    /**
     * Returns a product containing all available attributes from the given ID
     * @param productID the ID of the detailed product to fetch
     * @return
     */
    @Override
    public DetailedProduct fetchProduct(int productID) {
        return productCatalogue.fetchProduct(productID);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String searchCriteria) {
        return productCatalogue.searchProducts(searchCriteria);
    }


//    @Override
//
//    public IDisplayable fetchMedia(int productID) throws IOException, ClassNotFoundException {
//
//        cTool.sendString(String.valueOf(productID));
//
//        return ((PictureMedia) cTool.readObj());
//
//    }

    /**
     * Gets a HashMap of simple products.
     * @return HashMap containing non-detailed products.
     */
    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return productCatalogue.fetchProductOverview();
    }

    /**
     * Permanently saves the changes made to the current product.
     */
    @Override
    public void saveChanges() {
        productCatalogue.saveChanges(currentProduct);
    }

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistence.
     */
    @Override
    public void collectERPProducts() {
        productCatalogue.saveProductsFromERP(new ERPFetcher().getProducts());
    }

    /**
     * Returns the current product.
     * @return a detailed product.
     */
   @Override
    public DetailedProduct getCurrentProduct() {
        return this.currentProduct;
    }

    /**
     * Sets the variable "currentProduct" to the specifide product.
     * @param productID ID of the product to be modified.
     */
    @Override
    public void setCurrentProduct(int productID) {
        currentProduct = fetchProduct(productID);
    }

    /**
     * Returns the root of the tree of categories.
     * @return the root category object.
     */
    @Override
    public Category getRootCategory() {
        return productCatalogue.fetchRootCategory();
    }

    /**
     * Adds the current product to the chosen category.
     * @param categoryName Name of the category to add the current product to.
     */
    @Override
    public void addProductToCategory(String categoryName) {
        productCatalogue.addProductToCategory(currentProduct, categoryName);
    }

    /**
     * Gets a list of all the available categories.
     * @return a list object containing all categories
     */
    @Override
    public List<Category> getAllCategories() {
        return productCatalogue.getAllCategories();
    }

    /**
     * Creates a new category with the chosen name and parent
     * @param categoryName Name of the new category
     * @param parent Parent of the new category
     */
    @Override
    public void addNewCategory(String categoryName, String parent) {
        productCatalogue.addNewCategory(categoryName, parent);
    }

    /**
     * Fetch a map of products.
     * @param categories a set of categories, from which products will be found.
     * @return a Hashmap containing products grouped by category.
     */
    @Override
    public HashMap<Integer, Product> fetchProductsByCategory(HashSet<Category> categories) {
        return productCatalogue.fetchProductsByCategory(categories);
    }

    /**
     * Fetch the thumbnail for the current selected product and stores it in the PIM Cache.
     * @return Returns the path to the thumbnail in the PIM Cache.
     */
    @Override
    public String fetchThumbnailPathForCurrentProduct() {

        String currentProductThumbnail = currentProduct.getMediaList().get(0).getPath();

        // Retrieve the media from the cache if it exists.
        File cacheMedia = new File(currentProductThumbnail);
        if (cacheMedia.exists()) {
            return cacheMedia.getAbsolutePath();
        } else {
            // Connect to the FTP server and save the image to the cache.
            FTPTool ftpTool = new FTPTool(FTPTool.username, FTPTool.password);
            return ftpTool.retrieveFile(currentProductThumbnail);
        }
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {

        Set<IDisplayable> displayables = productCatalogue.fetchMediaOverview();
        if (displayables != null) {
            if (displayables.size() > 0) {
                Set<String> paths = new HashSet<>();
                for (IDisplayable displayable : displayables) {
                    paths.add(displayable.getPath());
                }
                FTPTool ftpTool = new FTPTool(FTPTool.username, FTPTool.password);
                ftpTool.retrieveFiles(paths);
            }
        }
        return displayables;
    }

}

