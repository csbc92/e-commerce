package ecompim.Product;

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

    public Product(int productID, String shortDescription, double salePrice, String productName, int stock) {
        this.productID = productID;
        this.shortDescription = shortDescription;
        this.salePrice = salePrice;
        this.productName = productName;
        this.stock = stock;
        this.isHidden = true;
        this.tags = new TreeSet<>();
    }

    public int getProductID() {
        return productID;
    }

    public double getRating() {
        return rating;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public TreeSet<String> getTags() {
        return tags;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getName() {
        return productName;
    }

    public int getStock() {
        return stock;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setTag(String tag) {
        this.tags.add(tag);
    }

    public ArrayList<IDisplayable> getMediaList() {
        return new ArrayList<>(mediaList);
    }

    public ArrayList<Category> getCategoryList() {
        return new ArrayList<>(categoryList);
    }

    /*@Override
    public String toString() {
        return shortDescription;
    }*/
}
