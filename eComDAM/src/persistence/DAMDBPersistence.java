package persistence;

import Product.IDisplayable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Vedsted on 18-05-2017.
 */
public class DAMDBPersistence implements IDAMPersistence {

    private Connection connection;


    public DAMDBPersistence(String connectionString, String username, String password) {

        try {
            this.connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IDisplayable fetchMedia(String mediaID) {
        return null;
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        return null;
    }
}
