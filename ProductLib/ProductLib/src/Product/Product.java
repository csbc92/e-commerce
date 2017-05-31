package Product;

import java.io.Serializable;


public class Product implements Serializable{

    private int productID;
    private double rating;
    private boolean isHidden;
    private String shortDescription;
    protected double salePrice;
    private String productName;
    private int stock;


    /**
     * initializes a product
     * @param productID the productID
     * @param shortDescription the short description
     * @param salePrice the sales price
     * @param productName the name of the product
     * @param stock the stock
     */
    public Product(int productID, String shortDescription, double salePrice, String productName, int stock) {
        if (productID < 0) {
            throw new IllegalArgumentException("productID must not be negative.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("stock must not be negative");
        }
        this.productID = productID;
        this.shortDescription = shortDescription;
        this.salePrice = salePrice;
        this.productName = productName;
        this.stock = stock;
        this.isHidden = true;
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


}
