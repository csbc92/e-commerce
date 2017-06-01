package ecompim.businessLogic;


import Product.Category;
import Product.DetailedProduct;
import Product.IDisplayable;
import Product.Product;
import ecompim.ERPAccess.ERPFetcher;
import ecompim.servers.ServerHandler;
import network.ClientTool;
import network.FTPTool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is an implementation of the IPIM interface.
 */
public class PIMManager implements IPIM {

    private static final String FTPUSER = "b33_20125183";
    private static final String FTPPASS = "ecom1234";
    private static final String FTPHOST = "ftp.byethost33.com";
    private static String cachePath = System.getProperty("user.dir") + "/htdocs/files";
    private static final String DAMSERVER = "localhost";
    private static final int DAMPORT = 5678;

    private DetailedProduct currentProduct;
    private IProductCatalogue productCatalogue;
    private Thread netHandler;
    private ClientTool cTool;

    /**
     * initializes the PIMManager
     */
    public PIMManager() {

        productCatalogue = new ProductCatalogue();

        // Start the PIM server
        try {
            netHandler = new Thread(new ServerHandler(this, 6789));
            netHandler.setDaemon(true);
            netHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize a new client for communication with the DAM system.
        cTool = new ClientTool(DAMSERVER, DAMPORT);
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
     * @param parent       Parent of the new category
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
            FTPTool ftpTool = new FTPTool(FTPHOST, FTPUSER, FTPPASS);
            return ftpTool.retrieveFile(currentProductThumbnail);
        }
    }


    /**
     * Clears the cache in PIM
     * @return The number of files deleted.
     */
    @Override
    public int clearCache() {

        File cache = new File(cachePath);
        int filesDeletedAmount = deleteFilesRecursive(cache);

        return filesDeletedAmount;
    }


    /**
     * Util method to delete files recursively given the directory.
     * @param directory
     * @return The number of files deleted. Does not count the directories deleted.
     */
    private int deleteFilesRecursive(File directory) {
        int deletedFilesAmount = 0;

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The provided File must be a directory.");
        }

        for (File entry : directory.listFiles()) {

            if (entry.isDirectory()) {

                deletedFilesAmount += deleteFilesRecursive(entry);
                entry.delete(); // Delete the directory after it has been emptied.

            } else if (entry.isFile()) {

                entry.delete(); // Delete the file.
                deletedFilesAmount++;
            }
        }

        return deletedFilesAmount;
    }

    /**
     * fetches a Set of the type IDisplayable
     * @return
     */
    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        Set<IDisplayable> displayables = productCatalogue.fetchMediaOverview();

        if (displayables != null) {
            if (displayables.size() > 0) {
                Set<String> paths = new HashSet<>();
                for (IDisplayable displayable : displayables) {
                    paths.add(displayable.getPath());
                }

                FTPTool ftpTool = new FTPTool(FTPHOST, FTPUSER, FTPPASS);
                ftpTool.retrieveFiles(paths);
            }
        }

        return displayables;
    }
}