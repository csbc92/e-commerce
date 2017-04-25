package ecompim.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable{

    private int productID;
    private double rating;
    private boolean isHidden;
    private String[] tags;
    private String shortDescription;
    private double salePrice;
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

    public String[] getTags() {
        return tags;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getProductName() {
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

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public ArrayList<IDisplayable> getMediaList() {
        return new ArrayList<>(mediaList);
    }

    public ArrayList<Category> getCategoryList() {
        return new ArrayList<>(categoryList);
    }
}
