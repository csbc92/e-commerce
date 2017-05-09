/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb;

import java.net.URL;
import java.util.ResourceBundle;

import ecompim.Product.DetailedProduct;
import ecomweb.WEBBusinessLayer.IWebManager;
import ecomweb.WEBBusinessLayer.WebManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {
    private IWebManager webManager;


    @FXML
    private ImageView ivProductImg;
    @FXML
    private Label lProductName;
    @FXML
    private Label lProductID;
    @FXML
    private Label lRating;
    @FXML
    private Label lPrice;
    @FXML
    private Button btnAddToCart;
    @FXML
    private Label lStock;
    @FXML
    private TextArea taShortDescription;
    @FXML
    private Label lLongDescriptionSection;
    @FXML
    private TextArea taLongDescription;
    @FXML
    private TableView<?> tabvSpecifications;
    @FXML
    private TableColumn<?, ?> tcSpecifications1;
    @FXML
    private TableColumn<?, ?> tcSpecifications2;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       webManager = new WebManager();
       displayProduct(1);


    }

    private void displayProduct(int productID){
        //Test code to see the GUI works, and fetching products work
        DetailedProduct product = webManager.getProduct(productID);
        lPrice.setText(""+product.getSalePrice()); // Is this really the best way of converting a number to a string?
        lProductName.setText(product.getName());
        lProductID.setText(""+product.getProductID());
        taLongDescription.setText(product.getLongDescription());

    }


    // For testing purposes the page will refresh, when the add to cart button is pressed
    @FXML
    public void displayProduct(ActionEvent actionEvent) {
        displayProduct(1);
    }
}
