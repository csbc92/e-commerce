package ecompim.PIMPersistence;import Product.*;import java.io.*;import java.net.URI;import java.net.URISyntaxException;import java.net.URL;import java.util.ArrayList;import java.util.HashMap;import java.util.Map;/** * Created by Vedsted on 24-04-2017. */public class PIMFilePersistence implements IPIMPersistence {    private File productFile;    private File categoryFile;    /**     * Initializes the file persistence where the parameter is the path to the file to load/save to     *     * @param productFilePath the path to the file to load from & save to     */    public PIMFilePersistence(String productFilePath, String categoryFilePath) {        productFile = new File(productFilePath);        categoryFile = new File(categoryFilePath);    }    @Override    public HashMap<Integer, Product> fetchProductOverview() {        HashMap<Integer, Product> products = new HashMap<>();        products.putAll(fetchDetailedProducts());        return products;    }    @Override    public DetailedProduct fetchProduct(int productID) {        return fetchDetailedProducts().get(productID);    }    /**     * Fetches detailedproducts from the productFile     *     * @return a map containing Integers as keys and DetailedProducts as values     */    private HashMap<Integer, DetailedProduct> fetchDetailedProducts() {        HashMap<Integer, DetailedProduct> products = new HashMap<>();        try (FileInputStream fis = new FileInputStream(productFile);             ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {            DetailedProduct p;            while (fis.available() > 0) {                if ((p = (DetailedProduct) objectInputStream.readObject()) != null) {                    products.put(p.getProductID(), p);                }            }        } catch (IOException | ClassNotFoundException e) {            e.printStackTrace();        }        return products;    }    @Override    public void storeProducts(Map<Integer, DetailedProduct> products) {        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(productFile, false))) {            for (DetailedProduct p : products.values()) {                objectOutputStream.writeObject(p);            }        } catch (IOException e) {            e.printStackTrace();        }    }    @Override    public void saveProduct(DetailedProduct product) {        Map<Integer, DetailedProduct> temp = fetchDetailedProducts();        temp.put(product.getProductID(), product);        storeProducts(temp);    }    @Override    public HashMap<Integer, Product> searchProducts(String value) {        HashMap<Integer, Product> products = fetchProductOverview();        HashMap<Integer, Product> toReturn = new HashMap<>();        for (Map.Entry<Integer, Product> entry : products.entrySet()) {            Product p = entry.getValue();            Integer key = entry.getKey();            if (String.valueOf(p.getProductID()).toLowerCase().contains(value.toLowerCase()) || p.getName().toLowerCase().contains(value.toLowerCase())                    || String.valueOf(p.getShortDescription()).toLowerCase().contains(value.toLowerCase())) { // TODO more criteria support                toReturn.put(key, p);            }        }        return toReturn;    }    @Override    public HashMap<Integer, Product> getCategoryOverview(Category category) {        throw new UnsupportedOperationException();    }    @Override    public Category fetchRootCategory() {        Category rootCategory = null;        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(categoryFile))) {             rootCategory = (Category) objectInputStream.readObject();        } catch (FileNotFoundException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        } catch (ClassNotFoundException e) {            e.printStackTrace();        }        return rootCategory; //TODO needs testing    }        @Override    public void saveRootCategory(Category rootCategory) {        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(categoryFile, false))) {            objectOutputStream.writeObject(rootCategory);        } catch (FileNotFoundException e) {            e.printStackTrace();        } catch (IOException e) {            e.printStackTrace();        }    }}