package ecompim.PIMPersistence;

import Product.*;
import ecompim.SQL.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMDBPersistence implements IPIMPersistence{

    private SQL DB;

    /**
     * Initializes the database persistence. This requires the url, username and password for the database
     * @param connectionString the URL to the database to connect to
     * @param username the username to login with
     * @param password the password to login with
     */
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

    @Override
    public HashMap<Integer,Product> searchProducts(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Category fetchRootCategory() {
        return null;
    }

    @Override
    public void saveRootCategory(Category rootCategory) {

    }


}
