package ecompim.Product;

import java.io.Serializable;
import java.util.HashMap;


public class DetailedProduct extends Product implements Serializable {

    private String longDescription;
    private HashMap<String,String> technicalDetails;
    private double weight;
    private double height;
    private double length;
    private double width;


    public DetailedProduct(int productID, String shortDescription, double salePrice, String productName, int stock, double weight, double height, double length, double width) {
        super(productID, shortDescription, salePrice, productName, stock);
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public HashMap<String, String> getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(HashMap<String, String> technicalDetails) {
        this.technicalDetails = technicalDetails;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}
