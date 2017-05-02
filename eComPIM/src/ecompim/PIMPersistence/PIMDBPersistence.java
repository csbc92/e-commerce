package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;
import ecompim.SQL.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMDBPersistence implements IPIMPersistence{

    private SQL DB;
    public PIMDBPersistence(String connectionString, String username, String password) {
        DB = new SQL(connectionString,username,password);

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
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveProduct(DetailedProduct product) {
        throw new UnsupportedOperationException();
    }


}
