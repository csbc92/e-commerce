package persistence;import Product.IDisplayable;import Product.PictureMedia;import network.FTPTool;import java.sql.Connection;import java.sql.ResultSet;import java.sql.SQLException;import java.util.HashSet;import java.util.Set;public class DAMDBPersistence implements IDAMPersistence {    private Connection connection;    private SQLTool db;    private FTPTool ftpTool;    public DAMDBPersistence(String connectionString, String username, String password) {        db = new SQLTool(connectionString, username, password);        ftpTool = new FTPTool("", "");    }    @Override    public IDisplayable fetchMedia(Integer mediaID) {        IDisplayable media = null;        try {            System.out.println("fetching from db");            String query = "SELECT * FROM media WHERE id = " + mediaID + ";";            db.close();            db.clear();            db.add(query);            db.open();            ResultSet rs = db.getResultSet();            boolean aTupleWasFound = false;            while (rs.next()) {                aTupleWasFound = true;                System.out.println(rs.getInt("id") + rs.getString("path"));                //String path = ftpTool.retrieveFile("htdocs/" + rs.getString("path"));                String path = "htdocs/" + rs.getString("path");                media = new PictureMedia(rs.getInt("id"), path);            }            if (!aTupleWasFound) {                media = new PictureMedia(0, "../files/qmark.png");            }        } catch (SQLException e) {            e.printStackTrace();        }        return media;    }    @Override    public Set<IDisplayable> fetchMediaOverview() {        Set<IDisplayable> displayables = new HashSet<>();        IDisplayable media;        try {            db.close();            db.clear();            db.add("SELECT * FROM media Limit 10");            db.open();            ResultSet rs = db.getResultSet();            while (rs.next()) {                String path = "htdocs/" + rs.getString("path");                media = new PictureMedia(rs.getInt("id"), path);                displayables.add(media);            }            return displayables;        } catch (Exception e) {            e.printStackTrace();        }        return displayables;    }    @Override    public Set<IDisplayable> fetchMedia(Set<Integer> mediaIDs) {        if (mediaIDs == null) {            throw new IllegalArgumentException("mediaIDs must not be null");        }        Set<IDisplayable> displayables = new HashSet<>();        for (Integer mediaID : mediaIDs) {            IDisplayable media = fetchMedia(mediaID);            if (media != null) {                displayables.add(media);            }        }        return displayables;    }}