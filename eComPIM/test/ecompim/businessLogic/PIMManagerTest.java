package ecompim.businessLogic;



import Product.Product;
import ecompim.businessLogic.PIMManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by danie on 23-05-2017.
 */

public class PIMManagerTest {
    PIMManager pimManager = new PIMManager();



    @Test
    public void fetchProduct() throws Exception {

        assertEquals(10000, pimManager.fetchProduct(10000).getProductID());
    }

    @Test
    public void searchProducts() throws Exception {

        assertEquals(10,pimManager.searchProducts("1000").size());
    }

    @Test
    public void fetchMedia() throws Exception {

        pimManager.fetchMedia(10000).getID(); //TODO
    }

    @Test
    public void fetchProductOverview() throws Exception {

        assertEquals(50,pimManager.fetchProductOverview().size());
    }

    @Test
    public void saveChanges() throws Exception {
    }//Kan ses i webshop

    @Test
    public void getCurrentProduct() throws Exception {

        pimManager.setCurrentProduct(10000);
        assertEquals(10000, pimManager.getCurrentProduct().getProductID());
    }

    @Test
    public void setCurrentProduct() throws Exception {

        pimManager.setCurrentProduct(10000);
        assertEquals(10000, pimManager.getCurrentProduct().getProductID());
    }

    @Test
    public void getRootCategory() throws Exception {

        assertTrue("Produkt".equalsIgnoreCase(pimManager.getRootCategory().getName()));
    }

    @Test
    public void addProductToCategory() throws Exception {

        Product dummy = new Product(1,"d",2,"test",5);

        //TODO Kan ses i gui
    }

    @Test
    public void addNewCategory() throws Exception {
        //TODO kan ses i gui
    }

    @Test
    public void fetchProductsByCategory() throws Exception {
        //TODO kan ses i gui
    }
}
