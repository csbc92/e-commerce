package persistence;

import Product.IDisplayable;
import Product.PictureMedia;

import java.sql.*;
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
        IDisplayable media;

        try {
            Statement st = connection.createStatement();
            String query = "SELECT * FROM media WHERE id = " + mediaID +";";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                media = new PictureMedia(rs.getInt("id"), rs.getString("path"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        return null;
    }
}
