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

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        HashMap<Integer, Product> products = new HashMap<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(productFile))) {
            Product p;

            while ((p = (Product) objectInputStream.readObject()) != null) {
                products.put(p.getProductID(), p);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return null;
    }

    /**
     * @param products
     * takes map in and writes it to product file
     */
    @Override
    public void storeProducts(Map<Integer, Product> products) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(productFile, false))) {

            for (Product p : products.values()) {
                objectOutputStream.writeObject(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
