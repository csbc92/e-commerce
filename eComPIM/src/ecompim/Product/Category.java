package ecompim.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private Category parent;
    private ArrayList<Category> categoryList;
    private int level; // root == 0

    /**
     * Initializes a category with no parent
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = name;
        categoryList = new ArrayList<>();
        this.level = 0;
    }

    /**
     * Initializes a category with a parent
     * @param name the name of the category
     * @param parent the parent category
     */
    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
        this.level = parent.getLevel() + 1;
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
}
