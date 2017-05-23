package Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


public class DetailedProduct extends Product implements Serializable {

    private String longDescription;
    private HashMap<String,String> technicalDetails;
    private double margin;
    private double costPrice;
    private ArrayList<IDisplayable> mediaList;
    private ArrayList<Category> categoryList;
    private TreeSet<String> tags;


    /**
     * Initializes the DetailedProduct
     * @param productID the productID
     * @param shortDescription the short description
     * @param costPrice the cost price
     * @param productName the product name
     * @param stock the current stock of the product
     */
    public DetailedProduct(int productID, String shortDescription, double margin, String productName, int stock, double costPrice) {
        super(productID, shortDescription, costPrice*(margin/100 + 1), productName, stock);
        this.costPrice = costPrice;
        this.margin = margin;
        this.longDescription = shortDescription;
        this.mediaList = new ArrayList<>();
        this.tags = new TreeSet<>();

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

    /**
     * returns the tags
     * @return a TreeSet containing strings of tags
     */
    public TreeSet<String> getTags() {
        return tags;
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
    /*public ArrayList<Category> getCategoryList() {
        return new ArrayList<>(categoryList);
    }*/

    /**
     * adds a category to the categoryList based on the name and teh parent category
     * @param name the name of the category
     * @param parent the parent category to this category
     */
    /*public void addCategory(String name, Category parent) {
        this.categoryList.add(new Category(name, parent,-1));
    } */

    /**
     * adds a media of IDIsplayable to the medialist
     * @param media
     */
    public void addMedia(IDisplayable media){
        this.mediaList.add(0,media);
    }


}
