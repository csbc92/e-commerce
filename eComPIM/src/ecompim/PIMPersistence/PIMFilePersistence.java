package ecompim.PIMPersistence;


import Product.Category;
import Product.DetailedProduct;
import Product.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PIMFilePersistence implements IPIMPersistence {


    private File productFile;
    private File categoryFile;


    /**
     * Initializes the file persistence where the parameter is the path to the file to load/save to
     *
     * @param productFilePath the path to the file to load from & save to
     */
    public PIMFilePersistence(String productFilePath, String categoryFilePath) {
        productFile = new File(productFilePath);
        categoryFile = new File(categoryFilePath);
    }


    /**
     * @return
     */
    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        HashMap<Integer, Product> products = new HashMap<>();
        products.putAll(fetchDetailedProducts());
        return products;
    }

    /**
     * Fetches a detailed product object with the given ID
     *
     * @param productID the ID of the detailed product to fetch
     * @return an instance of DetailedProduct matching the specified ID.
     */
    @Override
    public DetailedProduct fetchProduct(int productID) {
        return fetchDetailedProducts().get(productID);
    }


    /**
     * Fetches detailed products from the productFile
     *
     * @return a map containing Integers as keys and DetailedProducts as values
     */
    private HashMap<Integer, DetailedProduct> fetchDetailedProducts() {
        HashMap<Integer, DetailedProduct> products = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(productFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
            DetailedProduct p;
            while (fis.available() > 0) {
                if ((p = (DetailedProduct) objectInputStream.readObject()) != null) {
                    products.put(p.getProductID(), p);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Saves the products in the given map to a file via an ObjectOutputStream.
     * Overwrites the current content on the file.
     *
     * @param products the products to save
     */
    @Override
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(productFile, false))) {
            for (DetailedProduct p : products.values()) {
                objectOutputStream.writeObject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a specific product to the saved file of products.
     *
     * @param product the product that is to be saved.
     */
    @Override
    public void saveProduct(DetailedProduct product) {
        Map<Integer, DetailedProduct> temp = fetchDetailedProducts();
        temp.put(product.getProductID(), product);
        storeProducts(temp);
    }

    /**
     * Search the map of products for a given String value.
     *
     * @param value the value to search for
     * @return a HashMap of products containing the given String Value
     */
    @Override
    public HashMap<Integer, Product> searchProducts(String value) {
        HashMap<Integer, Product> products = fetchProductOverview();
        HashMap<Integer, Product> toReturn = new HashMap<>();
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product p = entry.getValue();
            Integer key = entry.getKey();
            if (String.valueOf(p.getProductID()).toLowerCase().contains(value.toLowerCase()) || p.getName().toLowerCase().contains(value.toLowerCase())
                    || String.valueOf(p.getShortDescription()).toLowerCase().contains(value.toLowerCase())) { // TODO more criteria support
                toReturn.put(key, p);
            }
        }
        return toReturn;
    }


    @Override
    public HashMap<Integer, Product> getCategoryOverview(Category category) {
        throw new UnsupportedOperationException();
    }

    /**
     * Fetches the root of the category tree.
     *
     * @return instance of category that is the root
     */
    @Override
    public Category fetchRootCategory() {
        Category rootCategory = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(categoryFile))) {
            rootCategory = (Category) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rootCategory; //TODO needs testing
    }

    @Override
    public Set<Integer> getMediaIDs(int productID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxCatId() {
        throw new UnsupportedOperationException();
    }

    /**
     * saves a cateogry object as the root of the category tree.
     *
     * @param rootCategory Category object to be saved as root.
     */
    @Override
    public void saveRootCategory(Category rootCategory) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(categoryFile, false))) {
            objectOutputStream.writeObject(rootCategory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

