package ecomweb.WEBBusinessLayer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Created by danie on 23-05-2017.
 */
public class WebManagerTest {
    @Test
    public void getProduct() throws Exception {
        WebManager webManager = new WebManager();
        assertEquals(10000,webManager.getProduct(10000).getProductID());
        assertEquals(-1,webManager.getProduct(0).getProductID());
        assertEquals(-1,webManager.getProduct(8000).getProductID());
    }

}