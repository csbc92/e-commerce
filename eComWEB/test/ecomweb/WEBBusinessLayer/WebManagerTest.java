package ecomweb.WEBBusinessLayer;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebManagerTest {
    @Test
    public void getProduct() throws Exception {
        WebManager webManager = new WebManager();
        assertEquals(10000,webManager.getProduct(10000).getProductID());
        assertEquals(null,webManager.getProduct(-5));
    }

}