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
        return null;
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return null;
    }

    @Override
    public void storeProducts(Map<Integer, Product> products) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(productFile, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Product p : products.values()) {
                objectOutputStream.writeObject(p);
            }

            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
