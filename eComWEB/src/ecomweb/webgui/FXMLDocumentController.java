/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb.webgui;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Product.DetailedProduct;
import ecomweb.WEBBusinessLayer.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

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
    public TableView<Map.Entry<String, String>> tblViewTechDetails;
    @FXML
    private TableColumn<?, ?> tcSpecifications1;
    @FXML
    private TableColumn<?, ?> tcSpecifications2;

    private int productID;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       webManager = new WebManager();
       productID = 10000;
       displayProduct(productID);


    }

    /**
     * displays a product based on the id given
     * @param productID the id of the product to display
     */
    private void displayProduct(int productID){
        //Test code to see the GUI works, and fetching products work
        DetailedProduct product = webManager.getProduct(productID);

        if(product != null) {

            lPrice.setText("Pris: " + product.getSalePrice() + " dkk"); // Is this really the best way of converting a number to a string?
            lProductName.setText(product.getName());
            lProductID.setText("ID: " + product.getProductID());
            taLongDescription.setText(product.getLongDescription());
            populateTechnicalDetails(tblViewTechDetails, product);


            //File file = new File(webManager.getMediaPath(product.getMediaList().get(0).getPath()));

            //String path = "../htdocs/files/victor.jpg";
            //File file = new File(path);

            String path = product.getMediaList().get(0).getPath();
            System.out.println(path);
            File file = new File(path);


            //File file = product.getMediaList().get(0).getMedia();
            Image image;

            if (file.exists()) {
                System.out.println("image exists");
                image = new Image(file.toURI().toString());
            } else {
                System.out.println("fetching image");
                String[] strings = product.getMediaList().get(0).getPath().split("htdocs");
                System.out.println("path controller: " + strings[1]);

                String ultPath = webManager.getMediaPath(strings[1]);
                System.out.println(ultPath);
                file = new File(ultPath);
                image = new Image(file.toURI().toString());
            }

            ivProductImg.setImage(image);


            taShortDescription.setText(product.getShortDescription());
        } else {
            lProductName.setText("Error in getting the product");
        }


    }

    /**
     * Populates technical details into the specified TableView
     * @param tblView
     * @param product
     */
    private void populateTechnicalDetails(TableView<Map.Entry<String, String>> tblView, DetailedProduct product) {
        // First string is the technical property, second string is the technical description
        Map<String, String> techDetailsMap = product.getTechnicalDetails();

        //region Column definitions..
        // See this for reference: http://stackoverflow.com/questions/18618653/binding-hashmap-with-tableview-javafx
        TableColumn<Map.Entry<String, String>, String> techProperty = new TableColumn<>("Egenskab");
        techProperty.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // use the key as first column
                return new ReadOnlyStringWrapper(p.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String, String>, String> techDescription = new TableColumn<>("Beskrivelse");
        techDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // use the value as second column
                return new ReadOnlyStringWrapper(p.getValue().getValue());
            }
        });
        //endregion

        // Add the technical details to an observable list and to the TableView
        ObservableList<Map.Entry<String, String>> technicalDetailsEntries = FXCollections.observableArrayList(techDetailsMap.entrySet());
        tblView.setItems(technicalDetailsEntries);
        // Add the columns to the TableView
        tblView.getColumns().setAll(techProperty, techDescription);

    }


    // For testing purposes the page will refresh, when the add to cart button is pressed
    @FXML
    public void displayProduct(ActionEvent actionEvent) {
        displayProduct(productID);
    }
}
