package ecomweb.WEBBusinessLayer;



import ecompim.Product.DetailedProduct;
import ecompim.businessLogic.IProductFetcher;
import ecompim.businessLogic.PIMManager;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class WebManager implements IWebManager{
    private IProductFetcher productFetcher;

    public WebManager() {
        productFetcher = new PIMManager();
    }


    @Override
    public DetailedProduct getProduct(int productID) {
     return productFetcher.fetchProduct(productID);
  }

}
