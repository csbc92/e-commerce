/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.WEBBusinessLayer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class WebManagerTest {
    WebManager webManager; 
    public WebManagerTest() {
        webManager = new WebManager();
    }

    @Test
    public void testGetProduct() {
        assertEquals(10000,webManager.getProduct(10000).getProductID());
        assertEquals(null,webManager.getProduct(-5));
    }    
}
