package ecompim.PIMPersistence;

import Product.Category;
import Product.DetailedProduct;
import Product.Product;
import ecompim.SQL.SQLTool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PIMDBPersistence implements IPIMPersistence {

    private SQLTool db;

    /**
     * Initializes the database persistence. This requires the url, username and password for the database
     *
     * @param connectionString the URL to the database to connect to
     * @param username         the username to login with
     * @param password         the password to login with
     */
    public PIMDBPersistence(String connectionString, String username, String password) {
        db = new SQLTool(connectionString, username, password);
    }

    /**
     * fetches a product overview of 50 products
     *
     * @return a HasMap with products
     */
    @Override
    public HashMap<Integer, Product> fetchProductOverview() {
        String query = "SELECT * FROM product ORDER BY productid LIMIT 50;";
        return getProducts(query);
    }


    /**
     * fetches a detailed product
     *
     * @param productID the ID of the detailed product to fetch
     * @return detailed product
     */
    @Override
    public DetailedProduct fetchProduct(int productID) {
        DetailedProduct product = null;
        db.close();
        db.clear();
        db.add("SELECT * FROM product WHERE productid = ?;");
        db.prepareStatement();
        db.addParameter(1, productID);
        db.open();
        ResultSet rs = db.getResultSet();
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
        }
        HashMap<String, String> tds = new HashMap<>();
        db.close();
        db.clear();
        db.add("SELECT key, value FROM technicaldetails WHERE productid = ?;");
        db.prepareStatement();
        db.addParameter(1, productID);
        db.open();
        rs = db.getResultSet();
        try {
            while (rs.next()) {
                tds.put(rs.getString("key"), rs.getString("value"));
            }
            if (!tds.isEmpty()) {
                product.setTechnicalDetails(tds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        db.clear();
        db.add("SELECT * FROM tags WHERE productid = ?");
        db.prepareStatement();
        db.addParameter(1, productID);
        db.open();
        rs = db.getResultSet();
        try {
            while (rs.next()) {
                product.setTag(rs.getString("tagname"));
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            return product;
        }
    }


    /**
     * Unsupported operation
     *
     * @param products the products to save
     */
    @Override
    public void storeProducts(Map<Integer, DetailedProduct> products) {
        throw new UnsupportedOperationException();
    }


    /**
     * saves a products changes to the Database
     *
     * @param product the product that is to be saved.
     */
    @Override
    public void saveProduct(DetailedProduct product) {
        db.close();
        db.clear();
        db.add("UPDATE product SET salesmargin = " + product.getMargin() + " WHERE productid = " + product.getProductID() + "; ");
        db.add("DELETE FROM media WHERE productId =" + product.getProductID() + ";");
        if (!product.getMediaList().isEmpty()) {
            db.add(String.format("INSERT INTO media VALUES( %d, %d, 0); ", product.getProductID(), product.getMediaList().get(0).getID()));
        }
        db.add("DELETE FROM tags WHere productID =" + product.getProductID() + ";");
        for (String tag : product.getTags()
                ) {
            db.add("INSERT INTO tags VALUES('" + tag + "'," + product.getProductID() + ");");
        }
        db.add("UPDATE product SET longdescription = ? WHERE productid = ?;");
        db.add("UPDATE product SET ishidden = ? WHERE productid = ?;");
        db.prepareStatement();
        db.addParameter(1, product.getLongDescription());
        db.addParameter(2, product.getProductID());
        db.addParameter(3, product.isHidden());
        db.addParameter(4, product.getProductID());
        db.execute();

    }

    /**
     * Search for products
     *
     * @param value the value to search for
     * @return a overview of a max of 50 products matching the search criteria
     */
    @Override
    public HashMap<Integer, Product> searchProducts(String value) {
        String query = String.format("SELECT * FROM product WHERE CAST(productId as TEXT) LIKE '%%%s%%'  " +
                "OR lower(name) LIKE lower('%%%s%%') ORDER BY Productid LIMIT 50", value, value);

        return getProducts(query);
    }


    /**
     * returns products from a category. max 50 products
     *
     * @param category the category to look for
     * @return a HashMap of products
     */
    @Override
    public HashMap<Integer, Product> getCategoryOverview(Category category) {
        String query = "SELECT * FROM product NATURAL JOIN productincategory WHERE categoryid = " + category.getId() + " ORDER BY productid LIMIT 50;";
        return getProducts(query);
    }


    /**
     * fetcches root category
     * If no root category is found, null is returned.
     * @return root category
     */
    @Override
    public Category fetchRootCategory() {
        Category cat = null;
        db.close();
        db.clear();
        db.add("SELECT * FROM category WHERE categoryid = ( SELECT childcategory AS categoryid FROM categoryrelationship WHERE parentcategory IS NULL);");
        db.prepareStatement();
        db.open();
        ResultSet rs = db.getResultSet();
        try {

            while (rs.next()) {
                cat = new Category(rs.getString("categorydisplayname"), rs.getInt("categoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addChildren(cat);
        addProducts(cat);
        return cat;
    }

    /**
     * Returns a Set of media IDs given the product ID.
     *
     * @param productID
     * @return
     */
    @Override
    public Set<Integer> getMediaIDs(int productID) {
        Set<Integer> mediaIDs = new HashSet<>();
        db.close();
        db.clear();
        db.add("SELECT * FROM Media WHERE productID = ?");
        db.prepareStatement();
        db.addParameter(1, productID);
        db.open();
        ResultSet rs = db.getResultSet();

        try {
            while (rs.next()) {
                mediaIDs.add(rs.getInt("mediaid"));
            }
            return mediaIDs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mediaIDs;
    }


    /**
     * adds children to category and to all sub categories
     *
     * @param cat category
     */
    private void addChildren(Category cat) {
        db.close();
        db.clear();
        db.add("SELECT * FROM category NATURAL JOIN (SELECT parentcategory, childcategory AS categoryID FROM categoryrelationship)as foo WHERE parentcategory = ?;");
        db.prepareStatement();
        db.addParameter(1, cat.getId());
        db.open();
        ResultSet rs = db.getResultSet();

        try {
            while (rs.next()) {
                Category child = new Category(rs.getString("categorydisplayname"), cat, rs.getInt("categoryid"));
                cat.addChild(child);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Category cat2 : cat.getChildren()) {
            addChildren(cat2);
        }

    }


    /**
     * places products in categories
     *
     * @param cat category
     */
    private void addProducts(Category cat) {
        db.close();
        db.clear();
        db.add("SELECT productid FROM productInCategory WHERE categoryid = ?");
        db.prepareStatement();
        db.addParameter(1, cat.getId());
        db.open();
        try {

            ResultSet rs = db.getResultSet();
            while (rs.next()) {
                cat.addProductID(rs.getInt("productId"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Category c : cat.getChildren()) {
            addProducts(c);
        }
    }


    /**
     * Unsupported Opertion
     *
     * @param rootCategory
     */
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

    /**
     * Saves products into their categories in the database.
     *
     * @param rootCategory the root of the category tree.
     * @throws SQLException
     */
    private void saveProductsInCat(Category rootCategory) throws SQLException {
        for (Integer id : rootCategory.getProductIDSet()) {
            db.close();
            db.clear();
            db.add("SELECT * FROM productInCategory WHERE categoryId = ? AND productId = ?;");
            db.prepareStatement();
            db.addParameter(1, rootCategory.getId());
            db.addParameter(2, id);
            db.open();
            ResultSet rs = db.getResultSet();

            if (!rs.next()) // if it does not already exist in the database
            {
                db.close();
                db.clear();
                // System.out.println("NO WORKY WORKY");
                //TODO: Hvad sker der lige her?????
                db.add("INSERT INTO productInCategory VALUES(?,?);");
                db.prepareStatement();
                db.addParameter(1, rootCategory.getId());
                db.addParameter(2, id);
                db.execute();
                db.clear();
            } else {
                db.close();
                db.clear();
            }
        }
        for (Category child : rootCategory.getChildren()) {
            saveProductsInCat(child);
        }
    }


    /**
     * Saves the relationships (tree structure) between all categories to the database.
     *
     * @param rootCategory
     * @throws SQLException
     */
    private void saveCatRelationships(Category rootCategory) throws SQLException {
        for (Category child : rootCategory.getChildren()) {
            db.close();
            db.clear();
            db.add("SELECT * FROM categoryrelationship WHERE parentcategory = ? AND childcategory = ?;");
            db.prepareStatement();
            db.addParameter(1, rootCategory.getId());
            db.addParameter(2, child.getId());
            db.open();
            ResultSet rs = db.getResultSet();
            if (!rs.next()) {

                db.close();
                db.clear();
                db.add("INSERT INTO categoryrelationship VALUES(? ,?);");
                db.prepareStatement();
                db.addParameter(1, rootCategory.getId());
                db.addParameter(2, child.getId());
                db.execute();
                db.clear();
            } else {
                db.close();
                db.clear();
            }
        }
        for (Category child : rootCategory.getChildren()) {
            saveCatRelationships(child);
        }
    }


    /**
     * Saves all categories to the database.
     *
     * @param root
     * @throws SQLException
     */
    private void saveToCatTable(Category root) throws SQLException {
        db.close();
        db.clear();
        db.add("SELECT * FROM category WHERE categoryid = ?;");
        db.prepareStatement();
        db.addParameter(1, root.getId());
        db.open();
        ResultSet rs = db.getResultSet();
        if (!rs.next()) {
            System.out.println("WOOOP");
            db.close();
            db.clear();
            // System.out.println(root.getId());
            //TODO: Hvad sker der lige her?????
            db.add("INSERT INTO category VALUES (?,?);");
            db.prepareStatement();
            db.addParameter(1, root.getId());
            db.addParameter(2, root.getName());
            db.execute();
            db.clear();
        } else {
            db.close();
            db.clear();
        }
        for (Category c : root.getChildren()) {
            saveToCatTable(c);
        }
    }

    /**
     * fetches products from database from a given query
     *
     * @param query
     * @return a HashMap of products
     */
    private HashMap<Integer, Product> getProducts(String query) {
        HashMap<Integer, Product> products = new HashMap<>();
        db.close();
        db.clear();
        db.add(query);
        db.prepareStatement();
        db.open();
        ResultSet rs = db.getResultSet();
        try {
            while (rs.next()) {
                products.put(rs.getInt("productid"), new Product(rs.getInt("productid"),
                        rs.getString("shortdescription"),
                        rs.getDouble("costprice") * (rs.getDouble("salesMargin") / 100 + 1),
                        rs.getString("name"),
                        rs.getInt("stock")));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return products;
        }

    }

    /**
     * Returns the id in the DB with the highest value
     *
     * @return
     */
    public int getMaxCatId() {
        db.close();
        db.clear();
        db.add("SELECT MAX(categoryid) AS id FROM category");
        db.prepareStatement();
        db.open();
        try {

            ResultSet rs = db.getResultSet();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -12;
        } finally {
            db.close();
            db.clear();
        }


    }
}

