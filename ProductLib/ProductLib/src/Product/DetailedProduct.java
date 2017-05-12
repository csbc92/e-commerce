package Product;

import java.io.Serializable;
import java.util.HashMap;


public class DetailedProduct extends Product implements Serializable {

    private String longDescription;
    private HashMap<String,String> technicalDetails;
    private double weight;
    private double height;
    private double length;
    private double width;
    private double margin;
    private double costPrice;

    /**
     * Initializes the DetailedProduct
     * @param productID the productID
     * @param shortDescription the short description
     * @param costPrice the cost price
     * @param productName the product name
     * @param stock the current stock of the product
     * @param weight the weight
     * @param height the height
     * @param length the length
     * @param width the width
     */
    public DetailedProduct(int productID, String shortDescription, double costPrice, String productName, int stock, double weight, double height, double length, double width) {
        super(productID, shortDescription, costPrice, productName, stock);
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.costPrice = costPrice;
        this.margin = 0;
        this.longDescription = shortDescription;
    }

    /**
     * returns the long description
     * @return the long description
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * sets the long description
     * @param longDescription the long description to set
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * returns the tecnical details as a HashMap
     * @return the technical details as a HashMap with strings as keys and strings as values
     */
    public HashMap<String, String> getTechnicalDetails() {
        return technicalDetails;
    }

    /**
     * sets the technical details of the product
     * @param technicalDetails the technical details as a HashMap
     */
    public void setTechnicalDetails(HashMap<String, String> technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

    /**
     * returns the weight
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * returns the height
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * returns the length
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * returns the width
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * returns the margin
     * @return the margin
     */
    public double getMargin() {
        return margin;
    }

    /**
     * returns the cost price
     * @return the cost price
     */
    public double getCostPrice() {
        return costPrice;
    }

    /**
     * sets the margin and new sales price
     * @param margin the value of the new margin
     */
    public void setMargin(double margin) {
        this.margin = margin;
        this.salePrice = costPrice + costPrice * margin/100;
    }
}
