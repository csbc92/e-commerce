package persistence;import Product.IDisplayable;import Product.PictureMedia;import javafx.scene.image.Image;import networking.FTPTool;import org.apache.commons.net.ftp.FTPClient;import java.io.InputStream;import java.sql.*;import java.util.Set;/** * Created by Vedsted on 18-05-2017. */public class DAMDBPersistence implements IDAMPersistence {    private Connection connection;    private SQLTool db;    private FTPTool ftpTool;    public DAMDBPersistence(String connectionString, String username, String password) {        db = new SQLTool(connectionString, username, password);        ftpTool = new FTPTool("", "");    }    @Override    public IDisplayable fetchMedia(String mediaID) {        IDisplayable media = null;        try {            System.out.println("fetching from db");            db.close();            db.clear();            String query = "SELECT * FROM media WHERE id = " + mediaID + ";";            db.add(query);            db.open();            ResultSet rs = db.getResultSet();            while (rs.next()) {                InputStream input = ftpTool.retrieveFile("htdocs/" + rs.getString("path"));                Image image = new Image(input);                media = new PictureMedia(image);//                System.out.println(rs.getInt("id") + rs.getString("path"));////                media = new PictureMedia(rs.getInt("id"), rs.getString("path"));            }        } catch (SQLException e) {            e.printStackTrace();        }        return media;    }    @Override    public Set<IDisplayable> fetchMediaOverview() {        return null;    }}