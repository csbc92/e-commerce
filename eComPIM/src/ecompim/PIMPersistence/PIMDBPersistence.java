package ecompim.PIMPersistence;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMDBPersistence implements IPIMPersistence{

    private Connection connection;
    private String connectionString;
    private String username;
    private String password;

    public PIMDBPersistence(String connectionString, String username, String password) {
        //"jdbc:postgresql://localhost:5432/<INSERT DATABASE HERE>"
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;

        try {
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        throw new UnsupportedOperationException();
    }


}
