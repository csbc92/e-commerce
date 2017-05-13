/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author JV
 */
public class RootCategory implements Serializable {
   
    protected String name;
    protected ArrayList<Category> categoryList;
    protected HashSet<Integer> productIDSet;
    protected int level; // root == 0

    /**
     * Initializes a category with no parent
     * @param name the name of the category
     */
    public RootCategory(String name) {
        this.name = name;
        this.categoryList = new ArrayList<>();
        this.productIDSet = new HashSet<>();
        this.level = 0;
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
}
