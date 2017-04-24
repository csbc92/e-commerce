package ecompim.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private Category parent;
    private ArrayList<Category> categoryList;
    private int level; // root == 0


    public Category(String name) {
        this.name = name;
        categoryList = new ArrayList<>();
        this.level = 0;
    }

    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

    public void addChild(Category child) {
        this.categoryList.add(child);
    }

    public void removeChild(Category child) {
        this.categoryList.remove(child);
    }

    public Category getRoot() {
        if (level == 0) {
            return this;
        }
        return this.parent.getRoot();
    }

    public Category getParent() {
        return this.parent;
    }

    public int getLevel() {
        return level;
    }

    public List<Category> getChildren() {
        return categoryList;
    }
}
