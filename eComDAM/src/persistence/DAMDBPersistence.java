package persistence;import Product.IDisplayable;import Product.PictureMedia;import org.apache.commons.net.ftp.FTPClient;import java.sql.*;import java.util.Set;/** * Created by Vedsted on 18-05-2017. */public class DAMDBPersistence implements IDAMPersistence {    private Connection connection;    private SQLTool db;    public DAMDBPersistence(String connectionString, String username, String password) {        db = new SQLTool(connectionString, username, password);    }    @Override    public IDisplayable fetchMedia(String mediaID) {        IDisplayable media = null;        try {            System.out.println("fetching from db");            db.close();            db.clear();            String query = "SELECT * FROM media WHERE id = " + mediaID + ";";            db.add(query);            db.open();            ResultSet rs = db.getResultSet();            while (rs.next()) {                System.out.println(rs.getInt("id") + rs.getString("path"));                media = new PictureMedia(rs.getInt("id"), rs.getString("path"));            }        } catch (SQLException e) {            e.printStackTrace();        }        return media;    }    @Override    public Set<IDisplayable> fetchMediaOverview() {        return null;    }}