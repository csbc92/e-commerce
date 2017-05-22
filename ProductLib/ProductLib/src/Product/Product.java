package Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class Product implements Serializable{

    private int productID;
    private double rating;
    private boolean isHidden;
    private TreeSet<String> tags;
    private String shortDescription;
    protected double salePrice;
    private String productName;
    private int stock;
    private ArrayList<IDisplayable> mediaList;
    private ArrayList<Category> categoryList;

    /**
     * initializes a product
     * @param productID the productID
     * @param shortDescription the short description
     * @param salePrice the sales price
     * @param productName the name of the product
     * @param stock the stock
     */
    public Product(int productID, String shortDescription, double salePrice, String productName, int stock) {
        mediaList = new ArrayList<>();
        this.productID = productID;
        this.shortDescription = shortDescription;
        this.salePrice = salePrice;
        this.productName = productName;
        this.stock = stock;
        this.isHidden = true;
        this.tags = new TreeSet<>();
    }


    /**
     * returns the productID
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * returns the rating
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * returns whether the product is hidden
     * @return whether the product is hidden
     */
    public boolean isHidden() {
        return isHidden;
    }

    /**
     * returns the tags
     * @return a TreeSet containing strings of tags
     */
    public TreeSet<String> getTags() {
        return tags;
    }

    /**
     * returns the short description
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * returns the sales price
     * @return the sales price
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * returns the name
     * @return the name
     */
    public String getName() {
        return productName;
    }

    /**
     * returns the current stock
     * @return the current stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets the rating
     * @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * sets whether tge product is hidden
     * @param hidden
     */
    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    /**
     * adds a tag
     * @param tag
     */
    public void setTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * removes a tag
     * @param tag
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * returns an ArrayList of displayable media
     * @return an ArrayList containing IDisplayable objects
     */
    public ArrayList<IDisplayable> getMediaList() {
        return new ArrayList<>(mediaList);
    }

    /**
     * returns an ArrayList of categories
     * @return an ArrayList containing categories
     */
    public ArrayList<Category> getCategoryList() {
        return new ArrayList<>(categoryList);
    }

    /**
     * adds a category to the categoryList based on the name and teh parent category
     * @param name the name of the category
     * @param parent the parent category to this category
     */
    public void addCategory(String name, Category parent) {
        this.categoryList.add(new Category(name, parent,-1));

    }

    public void addMedia(IDisplayable media){

        this.mediaList.add(0,media);
    }

}
