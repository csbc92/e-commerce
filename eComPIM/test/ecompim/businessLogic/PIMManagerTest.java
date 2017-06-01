/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.businessLogic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class PIMManagerTest {
    PIMManager pimManager;
    public PIMManagerTest() {
         pimManager = new PIMManager();
    }

    @Test
    public void testFetchProduct() {
        assertEquals(10000, pimManager.fetchProduct(10000).getProductID());
    }

    @Test
    public void testSearchProducts() {
        assertEquals(10,pimManager.searchProducts("1000").size());
    }

    @Test
    public void testFetchProductOverview() {
         assertEquals(50,pimManager.fetchProductOverview().size());
    }

    @Test
    public void testGetCurrentProduct() {
         pimManager.setCurrentProduct(10000);
        assertEquals(10000, pimManager.getCurrentProduct().getProductID());
    }

    @Test
    public void testSetCurrentProduct() {
         pimManager.setCurrentProduct(10000);
        assertEquals(10000, pimManager.getCurrentProduct().getProductID());
    }

    @Test
    public void testGetRootCategory() {
          assertTrue("Produkt".equalsIgnoreCase(pimManager.getRootCategory().getName()));
    }
}
