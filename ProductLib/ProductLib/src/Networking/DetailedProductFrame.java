package Networking;

import Product.DetailedProduct;

import java.io.Serializable;

/**
 * Created by danie on 25-05-2017.
 */
public class DetailedProductFrame implements Serializable {

    private StatusCode statusCode;
    private DetailedProduct product;

    public DetailedProductFrame(StatusCode statusCode, DetailedProduct product) {
        this.statusCode = statusCode;
        this.product = product;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public DetailedProduct getProduct() {
        return product;
    }
}
