package ecompim.PIMPersistence;

import ecompim.Product.Product;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMFilePersistence implements IPIMPersistence{


    private File productFile;

    public PIMFilePersistence(String filePath) {
        productFile = new File(filePath);
    }

    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        return null;
    }

    @Override
    public Product fetchProduct(int productID) {
        return null;
    }


}
