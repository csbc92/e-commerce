
package persistence;


import Product.IDisplayable;
import Product.PictureMedia;
import network.FTPTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DAMDBPersistence implements IDAMPersistence {

    public static final String FTPUSER = "b33_20125183";
    public static final String FTPPASS = "ecom1234";
    public static final String FTPHOST = "ftp.byethost33.com";
    private Connection connection;
    private SQLTool db;
    private FTPTool ftpTool;


    public DAMDBPersistence(String connectionString, String username, String password) {

        db = new SQLTool(connectionString, username, password);
        ftpTool = new FTPTool(FTPHOST, FTPUSER, FTPPASS);
    }

    /**
     * Fetch a specific IDisplayable based on a specific media ID.
     * @param mediaID id of desired media
     * @return
     */
    @Override
    public IDisplayable fetchMedia(Integer mediaID) {
        IDisplayable media = null;

        try {

            System.out.println("fetching from db");

            String query = "SELECT * FROM media WHERE id = ?;";
            db.close();
            db.clear();
            db.add(query);
            db.prepareStatement();
            db.addParameter(1, mediaID);
            db.open();
            ResultSet rs = db.getResultSet();

            boolean aTupleWasFound = false;
            while (rs.next()) {
                aTupleWasFound = true;

                String path = "htdocs/" + rs.getString("path");
                media = new PictureMedia(rs.getInt("id"), path);

            }
            if (!aTupleWasFound) {
                media = new PictureMedia(0, "../files/qmark.png");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return media;
    }

    /**
     * Fetch a set of IDisplayables.
     * The max number of IDisplayables to be returned is 10.
     * @return
     */
    @Override
    public Set<IDisplayable> fetchMediaOverview() {
        Set<IDisplayable> displayables = new HashSet<>();
        IDisplayable media;

        try {
            db.close();
            db.clear();
            db.add("SELECT * FROM media Limit 10");
            db.prepareStatement();
            db.open();
            ResultSet rs = db.getResultSet();
            while (rs.next()) {
                String path = "htdocs/" + rs.getString("path");
                media = new PictureMedia(rs.getInt("id"), path);
                displayables.add(media);
            }
            return displayables;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayables;
    }

    /**
     * Fetch a set of IDisplayables based on the given set of media IDs.
     * @param mediaIDs
     * @return
     */
    @Override
    public Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs) {

        if (mediaIDs == null) {
            throw new IllegalArgumentException("mediaIDs must not be null");
        }

        Set<IDisplayable> displayables = new HashSet<>();

        for (Integer mediaID : mediaIDs) {
            IDisplayable media = fetchMedia(mediaID);

            if (media != null) {
                displayables.add(media);
            }
        }
        return displayables;
    }

}

