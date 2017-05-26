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
     * @param margin the margin in whole positive/negative number e.g. 20 if the sale price on the product is (cost price + 20%). Or -90 for a sale price price of (cost price -90%)
     */
    public DetailedProduct(int productID, String shortDescription, double margin, String productName, int stock, double costPrice) {
        super(productID, shortDescription, costPrice*(margin/100 + 1), productName, stock);
        if (costPrice < 0) {
            throw new IllegalArgumentException("costPrice must not be negative.");
        }
        this.costPrice = costPrice;
        this.setMargin(margin);
        this.longDescription = shortDescription;
        this.mediaList = new ArrayList<>();
        this.tags = new TreeSet<>();
        this.technicalDetails = new HashMap<>();

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
     * Returns a copy of the tecnical details as a HashMap
     * @return the technical details as a HashMap with strings as keys and strings as values
     */
    public HashMap<String, String> getTechnicalDetails() {
        return new HashMap<>(technicalDetails);
    }

    /**
     * Adds the specified key and value to the technical details.
     * If the key already exists, the value will be overridden.
     * @param key
     * @param value
     */
    public String addTechnicalDetail(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("The value must not be null");
        }

        return this.technicalDetails.put(key, value);
    }

    /**
     * Removes the technical detail with the specified key, if it exists.
     * @param key
     * @return The value that was removed or null if the key does not exist.
     */
    public String removeTechnicalDetail(String key) {
        return this.technicalDetails.remove(key);
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
     * @param margin the margin in whole positive/negative number e.g. 20 if the sale price on the product is (cost price + 20%). Or -90 for a sale price price of (cost price -90%)
     */
    public void setMargin(double margin) {
        if (margin < -90) {
            throw new IllegalArgumentException("margin must not be less than -90.");
        }
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
        if (media == null) {
            throw new IllegalArgumentException("media must not be null");
        }

        this.mediaList.add(0,media);
    }

    /**
     * Removes a media with the specified id
     * @param id
     */
    public boolean removeMedia(int id) {

        IDisplayable result = this.getMedia(id);

        // If no entry was found, always return null.
        if (result == null) {
            return false;
        }

        // Remove the media and return the result.
        return this.mediaList.remove(result);
    }

    /**
     * Search for a specific IDisplayable
     * @param id The ID to search for
     * @return Return the IDisplayable or null if not found.
     */
    public IDisplayable getMedia(int id) {
        IDisplayable result = null;

        // Linear search since the datastructure is an unsorted List
        for (IDisplayable displayable : this.mediaList) {
            if (id == displayable.getID()) {
                result = displayable;
                break;
            }
        }

        return result;
    }


}
