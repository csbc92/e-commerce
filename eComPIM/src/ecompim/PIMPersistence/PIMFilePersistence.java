package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMFilePersistence implements IPIMPersistence {


    private File productFile;

    public PIMFilePersistence(String filePath) {
        productFile = new File(filePath);
    }

    public HashMap<Integer, DetailedProduct> fetchDetailedProducts() {
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

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        HashMap<Integer, Product> products = new HashMap<>();
        products.putAll(fetchDetailedProducts());

        return products;
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return  fetchDetailedProducts().get(productID);
    }

    /**
     * @param products takes map in and writes it to product file
     */
    @Override
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(productFile, false))) {

            for (Product p : products.values()) {
                objectOutputStream.writeObject(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveProduct(DetailedProduct product) {
        Map<Integer, DetailedProduct> temp = fetchDetailedProducts();
        temp.put(product.getProductID(), product);
        storeProducts(temp);
    }
}
