/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author danie
 */
public class DetailedProductTest {
    
    public DetailedProductTest() {
    }
    
     public DetailedProduct getPerfectDetailedProduct() {
        int id = 1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = 350.75;

        DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);
        return p;
    }
     
      @Test
    public void marginAndSalePriceTest() {
        // Instantiate the product
        int id = 1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = 1748;
        DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);

        // Test sale price
        assertEquals(2097.6, p.getSalePrice(),2);

        p.setMargin(25); // Set the margin to something else
        assertEquals(2185, p.getSalePrice(),2);
    }
 @Test(expected = IllegalArgumentException.class)
   public void technicalDetailsTest() {
        DetailedProduct p = getPerfectDetailedProduct();
        int technicalDetailsAmount = 100;

        // Fill the technical details
        for (int i = 0; i < technicalDetailsAmount; i++) {
            p.addTechnicalDetail("MyKey" + i, "MyValue" + i);
        }

        // Test the size of technical details
        assertEquals(technicalDetailsAmount, p.getTechnicalDetails().size());

        
            p.addTechnicalDetail(null, "MyNullValue");
        

        
            p.addTechnicalDetail("MyNullKey", null);
        

        // Test retrieval on specific key
        String retrieveKey = "MyKey3";
        String retrieveValue = p.getTechnicalDetails().get(retrieveKey);
        String expectedValue = "MyValue3";
        assertEquals(expectedValue, retrieveValue);

        // Test removal of specific key that exists
        p.removeTechnicalDetail(retrieveKey);
        retrieveValue = p.getTechnicalDetails().get(retrieveKey);
        assertEquals(null, retrieveValue);

        // Test removal of a key that does not exist
        String nonExistingKey = "N/A";
        p.removeTechnicalDetail(nonExistingKey);
        retrieveValue = p.getTechnicalDetails().get(nonExistingKey);
        assertEquals(null, retrieveValue);

        // Test replacement of technical details
        p.setTechnicalDetails(new HashMap<>());
        int expectedSize = 0;
        assertEquals(expectedSize, p.getTechnicalDetails().size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void mediaTest() {
        DetailedProduct p = getPerfectDetailedProduct();
        String mediaExtension = ".png";
        int mediaAmount = 100;
        int mediaTestID = 50;

        // Fill product with media
        for (int i = 0; i < mediaAmount; i++) {
            int mediaID = i;
            String mediaPath = "/my/path/media" + mediaID + mediaExtension;
            p.addMedia(new PictureMedia(mediaID, mediaPath));
        }
        assertEquals(mediaAmount, p.getMediaList().size());

        // Test insertion of null
        
            p.addMedia(null);
        

        // Test retrieval on specific id
        assertEquals(mediaTestID, p.getMedia(mediaTestID).getID());

        // Test removal of specific media that exists
        p.removeMedia(mediaTestID);
        assertEquals(null, p.getMedia(mediaTestID));

        // Test removal of media that does not exist
        boolean result = p.removeMedia(200);
        assertEquals(result, false);
    }
    @Test
    public void createPerfectProduct() {
        int id = 1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = 350.75;

        DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);

        assertEquals(id, p.getProductID());
        assertEquals(shortDescription, p.getShortDescription());
        assertEquals(margin, p.getMargin(),2);
        assertEquals(stock, p.getStock(),2);
        assertEquals(costPrice, p.getCostPrice(),2);
    }
     @Test(expected = IllegalArgumentException.class)
    public void createProductIDLessThan0() {
        int id = -1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = 350.75;

        
            DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);
       
    }
     @Test(expected = IllegalArgumentException.class)
    public void createProductStockLessThan0() {
        int id = 1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = -1;
        double costPrice = 350.75;

        
            DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);
    }
     @Test(expected = IllegalArgumentException.class)
    public void createProductCostPriceLessThan0() {
        int id = 1;
        String shortDescription = "short description";
        double margin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = -1;

        
            DetailedProduct p =  new DetailedProduct(id, shortDescription, margin, productName, stock, costPrice);
       
    }
     @Test(expected = IllegalArgumentException.class)
    public void createProductMarginLessThanNinety() {
        // Instantiate the product.
        int id = 1;
        String shortDescription = "short description";
        double illegalMargin = -91;
        double legalMargin = 20;
        String productName = "Product name";
        int stock = 30;
        double costPrice = 350.75;

        // Instantiate the product with illegal margin.
            DetailedProduct p;
           p =  new DetailedProduct(id, shortDescription, illegalMargin, productName, stock, costPrice);
        

        // Set illegal margin after instantiation.
        
           p =  new DetailedProduct(id, shortDescription, legalMargin, productName, stock, costPrice);
            p.setMargin(-91); // This should not be allowed - will result in a sale price of less than -90%
        
    }
}
