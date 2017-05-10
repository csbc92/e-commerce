package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMFilePersistence implements IPIMPersistence {

    private File productFile;


    public PIMFilePersistence(String filePath) {
//        productFile = new File(filePath);
//
//      TODO: Dette virker ikke på stier, som indeholder mellemrum.
        try {
            String fullPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
            int index = fullPath.indexOf("out"); //Dependant on what IDE the program is run from the path is different. Generally the .jar must be in an out or dist folder, with the data file outside of the out or dist folder.
            if(index == -1){
                index = fullPath.indexOf("dist");
            }
            String fullFilePath = fullPath.substring(5,index)+filePath;
            System.out.println(fullPath);
            productFile = new File(fullFilePath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }


    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        HashMap<Integer, Product> products = new HashMap<>();
        products.putAll(fetchDetailedProducts());

        return products;
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        return fetchDetailedProducts().get(productID);
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


    /**
     * @param products takes map in and writes it to product file
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

    @Override
    public void saveProduct(DetailedProduct product) {
        Map<Integer, DetailedProduct> temp = fetchDetailedProducts();
        temp.put(product.getProductID(), product);
        storeProducts(temp);
    }

    @Override
    public HashMap<Integer, Product> searchProducts(String value) {
        HashMap<Integer, Product> products = fetchProductOverview();
        HashMap<Integer, Product> toReturn = new HashMap<>();
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product p = entry.getValue();
            Integer key = entry.getKey();
            if (String.valueOf(p.getProductID()).toLowerCase().contains(value.toLowerCase()) || p.getName().toLowerCase().contains(value.toLowerCase())
                    || String.valueOf(p.getShortDescription()).toLowerCase().contains(value.toLowerCase())) { // TODO more criteria support

                toReturn.put(key,p);
            }
        }
        return toReturn;
    }
}
