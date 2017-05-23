package Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Category implements Serializable{

    private Category parent;
    private String name;
    private ArrayList<Category> categoryList;
    private HashSet<Integer> productIDSet;
    private int id;
    private int level; // root == 0


    /**
     * Initializes a category with a parent
     * @param name the name of the category
     * @param id the id of the category
     * @param parent the parent category
     */
    public Category(String name, Category parent, int id) {
        this.name = name;
        this.id = id;
        this.categoryList = new ArrayList<>();
        this.productIDSet = new HashSet<>();
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

    /**
     * initializes a root category
     * @param name the name of the category
     * @param id the id of the category
     */
    public Category(String name, int id) {
        this.name = name;
        this.id = id;
        this.categoryList = new ArrayList<>();
        this.productIDSet = new HashSet<>();
        this.level = 0;
    }


    /**
     * returns the id of the category
     * @return the id of the category as a int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the root category of this category
     * @return the root category
     */
    public Category getRoot() {
        if (level == 0) {
            return this;
        }
        return this.parent.getRoot();
    }

    /**
     * Returns the parent category of this category
     * @return the parent category
     */
    public Category getParent() {
        return this.parent;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a child category to this category
     * @param child the child category
     */
    public void addChild(Category child) {
        this.categoryList.add(child);
    }

    /**
     * Removes a child category from this category
     * @param child the child category to remove
     */
    public void removeChild(Category child) {
        this.categoryList.remove(child);
    }

    /**
     * Returns the level of this category
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns all the children of this category
     * @return a list of all the children
     */
    public List<Category> getChildren() {
        return categoryList;
    }


    public void addProductID(Integer ID){
        productIDSet.add(ID);
    }

    public HashSet<Integer> getProductIDSet() {
        return productIDSet;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

