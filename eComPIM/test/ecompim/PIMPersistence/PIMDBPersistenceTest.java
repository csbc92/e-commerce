/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.PIMPersistence;

import Product.DetailedProduct;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class PIMDBPersistenceTest {
    PIMDBPersistence pimdbPersistence;
    public PIMDBPersistenceTest() {
        pimdbPersistence = new PIMDBPersistence("jdbc:postgresql://localhost:5432/eComPIM", "postgres", "1234");
    }

    @Test
    public void testFetchProductOverview() {
         assertEquals(50,pimdbPersistence.fetchProductOverview().size()); //The method should only get the first 50 products
    }

    @Test
    public void testFetchProduct() {
        assertEquals(10000, pimdbPersistence.fetchProduct(10000).getProductID());
    }



    @Test
    public void testSaveProduct() {
        DetailedProduct dummy = new DetailedProduct(1,"d",2,"test",5,500);
        pimdbPersistence.saveProduct(dummy);
        assertEquals(dummy,pimdbPersistence.fetchProduct(1));
    }

    @Test
    public void testSearchProducts() {
        assertEquals(27,pimdbPersistence.searchProducts("1000").size());
        assertEquals(2,pimdbPersistence.searchProducts("The Big Bang Theory").size());
        assertEquals(0,pimdbPersistence.searchProducts("我").size());
        assertEquals(0,pimdbPersistence.searchProducts(null).size());
        assertEquals(50,pimdbPersistence.searchProducts("").size());
    }



    @Test
    public void testFetchRootCategory() {
        assertTrue("Produkt".equalsIgnoreCase(pimdbPersistence.fetchRootCategory().getName()));
    }

    @Test
    public void testGetMediaIDs() {
         int expectedAmountOfMedia = 1;
        Set<Integer> mediaIDs = pimdbPersistence.getMediaIDs(10000);
        assertEquals(expectedAmountOfMedia, mediaIDs.size());
    }

    
}
