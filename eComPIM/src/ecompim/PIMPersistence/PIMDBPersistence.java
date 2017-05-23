package ecompim.PIMPersistence;

import Product.*;
import ecompim.SQL.SQL;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class PIMDBPersistence implements IPIMPersistence {

    private SQL DB;

    /**
     * Initializes the database persistence. This requires the url, username and password for the database
     *
     * @param connectionString the URL to the database to connect to
     * @param username         the username to login with
     * @param password         the password to login with
     */
    public PIMDBPersistence(String connectionString, String username, String password) {
        DB = new SQL(connectionString, username, password);

    }


    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        String query = "SELECT * FROM product ORDER BY productid LIMIT 50;";
        return getProducts(query);
    }

    @Override
    public DetailedProduct fetchProduct(int productID) {
        DetailedProduct product = null;

        DB.add("SELECT * FROM product WHERE productid = " + productID + ";");
        DB.open();
        ResultSet rs = DB.getResultSet();

        try {
            while (rs.next()) {
                product = new DetailedProduct(rs.getInt("productid"),
                        rs.getString("shortdescription"),
                        rs.getDouble("salesMargin"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("costprice"));

                product.setLongDescription(rs.getString("longdescription"));
                product.setHidden(rs.getBoolean("ishidden"));
                product.setMargin(rs.getDouble("salesmargin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }


        HashMap<String, String> tds = new HashMap<>();
        DB.add("SELECT key, value FROM technicaldetails WHERE productid = " + productID + ";");
        DB.open();
        rs = DB.getResultSet();

        try {
            while (rs.next()) {
                tds.put(rs.getString("key"), rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
            product.setTechnicalDetails(tds);
        }

        DB.add("SELECT url AS path, mediaid AS id FROM media WHERE productid = " + product.getProductID() + ";");
        DB.open();
        rs = DB.getResultSet();

        try {
            while (rs.next()) {
                product.addMedia(new PictureMedia(rs.getInt("id"), rs.getString("path")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }

        DB.add("SELECT * FROM tags WHERE productid = " + product.getProductID());
        DB.open();

        rs = DB.getResultSet();

        try {
            while (rs.next()) {
                product.setTag(rs.getString("tagname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }

        return product;
    }

    @Override
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveProduct(DetailedProduct product) {
        DB.add("UPDATE product SET salesmargin = " + product.getMargin() + " WHERE productid = " + product.getProductID() + "; ");
        DB.add("DELETE FROM media WHERE productId =" + product.getProductID() + ";");
        DB.add("INSERT INTO media VALUES('" + product.getMediaList().get(0).getPath() + "', " + product.getProductID() + ", " +
                "" + product.getMediaList().get(0).getID() + ", 0); ");
        DB.add("DELETE FROM tags WHere productID =" + product.getProductID() + ";");
        for (String tag : product.getTags()
                ) {
            DB.add("INSERT INTO tags VALUES('" + tag + "'," + product.getProductID() + ");");
        }
        DB.add("UPDATE product SET longdescription = '" + product.getLongDescription() + "' WHERE productid = " + product.getProductID() + ";");
        DB.add("UPDATE product SET ishidden = " + product.isHidden() + " WHERE productid = " + product.getProductID() + ";");

        DB.execute();
        DB.clear();

    }

    @Override
    public HashMap<Integer, Product> searchProducts(String value) {

        String query = "SELECT * FROM product WHERE CAST(productId AS TEXT)  LIKE '" + value + "%' ORDER BY Productid LIMIT 50;";
        return getProducts(query);
    }

    @Override
    public HashMap<Integer, Product> getCategoryOverview(Category category) {

        String query = "SELECT * FROM product NATURAL JOIN productincategory WHERE categoryid = " + category.getId() + " ORDER BY productid LIMIT 50;";
        return getProducts(query);
    }

    @Override
    public Category fetchRootCategory() {
        Category cat = null;

        DB.add("SELECT * FROM category WHERE categoryid = ( SELECT childcategory AS categoryid FROM categoryrelationship WHERE parentcategory IS NULL);");
        DB.open();
        ResultSet rs = DB.getResultSet();

        try {
            while (rs.next()) {
                cat = new Category(rs.getString("categorydisplayname"), rs.getInt("categoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }

        addChildren(cat);

        addProducts(cat);

        return cat;
    }

    private void addChildren(Category cat) {
        DB.add("SELECT * FROM category NATURAL JOIN (SELECT parentcategory, childcategory AS categoryID FROM categoryrelationship)as foo WHERE parentcategory = " + cat.getId() + ";");
        DB.open();
        ResultSet rs = DB.getResultSet();

        try {
            while (rs.next()) {
                Category child = new Category(rs.getString("categorydisplayname"), cat, rs.getInt("categoryid"));
                cat.addChild(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }

        for (Category cat2 : cat.getChildren()
                ) {
            addChildren(cat2);
        }
    }

    private void addProducts(Category cat) {

        DB.add("SELECT productid FROM productInCategory WHERE categoryid =" + cat.getId());
        DB.open();

        try {
            ResultSet rs = DB.getResultSet();
            while (rs.next()) {
                cat.addProductID(rs.getInt("productId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.clear();
            DB.close();
        }

        for (Category c :
                cat.getChildren()
                ) {
            addProducts(c);
        }
    }

    @Override
    public void saveRootCategory(Category rootCategory) {
        try {
            saveToCatTable(rootCategory);
            saveCatRelationships(rootCategory);
            saveProductsInCat(rootCategory);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveProductsInCat(Category rootCategory) throws SQLException {

        for (Integer id : rootCategory.getProductIDSet()
             ) {
//            if (id == 10000)
//                System.out.println("GR8");

            DB.add("SELECT * FROM productInCategory WHERE categoryId = "+ rootCategory.getId() + " AND productId = "+id+";");
            DB.open();
            ResultSet rs = DB.getResultSet();

            try {
                rs.next();
                rs.getInt(1); // Only to trigger an exception
                DB.clear();
            }catch (PSQLException e) {


                DB.close();
                DB.clear();
                //System.out.println("NO WORKY WORKY");

                DB.add("INSERT INTO productInCategory VALUES(" + rootCategory.getId() + "," + id + ");");
                DB.execute();
                DB.clear();
            }
        }

        for (Category child : rootCategory.getChildren()
             ) {
            saveProductsInCat(child);
        }
    }

    private void saveCatRelationships(Category rootCategory) throws SQLException {

        for (Category child : rootCategory.getChildren()
             ) {
            DB.add("SELECT * FROM categoryrelationship WHERE parentcategory = "+ rootCategory.getId() + " AND childcategory = "+child.getId()+";");
            DB.open();
            ResultSet rs = DB.getResultSet();

            try {
                rs.next();
                rs.getInt(1); // Only to trigger an exception
                DB.close();
                DB.clear();

            } catch (PSQLException e) {
                DB.close();
                DB.clear();


                DB.add("INSERT INTO categoryrelationship VALUES(" + rootCategory.getId()+","+ child.getId()+");");
                DB.execute();
                DB.clear();

            }


        }





        for (Category child : rootCategory.getChildren()
                ) {
           saveCatRelationships(child);
        }

    }

    private void saveToCatTable(Category root) throws SQLException {



        DB.add("SELECT * FROM category WHERE categoryid = "+ root.getId()+";");
        DB.open();
        ResultSet rs = DB.getResultSet();

        try {
            rs.next();
            rs.getInt(1); // Only to trigger exception

            DB.close();
            DB.clear();

        }catch (PSQLException e ) {
            DB.close();
            DB.clear();

            System.out.println(root.getId());
                DB.add("INSERT INTO category VALUES (" + root.getId() + ",'" + root.getName() + "');");
                DB.execute();
                DB.clear();



        }


        for (Category c : root.getChildren()
             ) {
            saveToCatTable(c);

        }
    }


    private HashMap<Integer, Product> getProducts(String query) {
        HashMap<Integer, Product> products = new HashMap<>();

        DB.add(query);

        DB.open();
        ResultSet rs = DB.getResultSet();
        try {
            while (rs.next()) {
                products.put(rs.getInt("productid"), new Product(rs.getInt("productid"),
                        rs.getString("shortdescription"),
                        rs.getDouble("costprice") * (rs.getDouble("salesMargin")/100 + 1),
                        rs.getString("name"),
                        rs.getInt("stock")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close();
            DB.clear();
        }

        return products;
    }


}
