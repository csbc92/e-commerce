package Product;

import java.util.List;

public class Category extends RootCategory {

    private Category parent;

    /**
     * Initializes a category with a parent
     * @param name the name of the category
     * @param parent the parent category
     */
    public Category(String name, Category parent) {
        super(name);
        this.parent = parent;
        this.level = parent.getLevel() + 1;
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

}
