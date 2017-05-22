package ecompim.PIMPersistence;

import Product.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danie on 12-05-2017.
 */
public interface IPIMPersistenceFacade{

        public HashMap<Integer, Product> fetchProductOverview() ;

        public DetailedProduct fetchProduct(int productID);

        public void storeProducts(Map<Integer, DetailedProduct> products) ;

        public void saveProduct(DetailedProduct product);

        public HashMap<Integer, Product> searchProducts(String value);

        HashMap<Integer, Product> fetchCategoryOverview(Category category);

        public Category getRootCategory();

        public void saveRootCategory(Category rootCategory);
}
