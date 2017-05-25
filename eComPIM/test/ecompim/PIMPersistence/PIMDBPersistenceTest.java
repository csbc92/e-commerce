package ecompim.PIMPersistence;

import Product.DetailedProduct;
import ecompim.PIMPersistence.PIMDBPersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by danie on 23-05-2017.
 */
class PIMDBPersistenceTest {
    PIMDBPersistence pimdbPersistence;
    @BeforeEach
    void setUp() {
        pimdbPersistence = new PIMDBPersistence("jdbc:postgresql://localhost:5432/eComPIM", "postgres", "1234");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fetchProductOverview() {
        assertEquals(50,pimdbPersistence.fetchProductOverview().size()); //The method should only get the first 50 products
    }

    @Test
    void fetchProduct() {
        assertEquals(10000, pimdbPersistence.fetchProduct(10000).getProductID());
    }


    @Test
    void saveProduct() {
        DetailedProduct dummy = new DetailedProduct(1,"d",2,"test",5,500);
        pimdbPersistence.saveProduct(dummy);
        assertEquals(dummy,pimdbPersistence.fetchProduct(1));
    }

    @Test
    void searchProducts() {
        assertEquals(10,pimdbPersistence.searchProducts("1000").size());
        assertEquals(0,pimdbPersistence.searchProducts("hej").size());
        assertEquals(0,pimdbPersistence.searchProducts("我").size());
        assertEquals(0,pimdbPersistence.searchProducts(null).size());
        assertEquals(50,pimdbPersistence.searchProducts("").size());
    }

    @Test
    void getMediaIDs() {
        int expectedAmountOfMedia = 1;
        Set<Integer> mediaIDs = pimdbPersistence.getMediaIDs(10000);
        assertEquals(expectedAmountOfMedia, mediaIDs.size());
    }

    @Test
    void getCategoryOverview() {
       //TODO kan ses i gui
    }

    @Test
    void fetchRootCategory() {
        assertTrue("Produkt".equalsIgnoreCase(pimdbPersistence.fetchRootCategory().getName()));
    }

    @Test
    void saveRootCategory() {
        //TODO kan ses virke vha. gui
    }

}