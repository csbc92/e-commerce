/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.PIMGUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ecompim.businessLogic.PIMManager;
import ecompim.businessLogic.IPIM;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<Product> lvProducts;
    @FXML
    private GridPane gpviewPoduct;
    @FXML
    private GridPane gpOverview;
    @FXML
    private Label labID;
    @FXML
    private Label labName;
    @FXML
    public ImageView imgvPic;
    @FXML
    public TextField tfBuyPrice;
    @FXML
    public TextField tfMargin;
    @FXML
    public TextField tfSalesPrice;
    @FXML
    public RadioButton rbPublic;
    @FXML
    public ToggleGroup publicityStatus;
    @FXML
    public RadioButton rbHidden;
    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TreeView categoryTreeView;

    private IPIM manager;
    private HashMap<Integer,Product> products;
    private ArrayList<Product> productList;
    //private ObservableList<Product> oList= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       manager = new PIMManager();
       setListViewContents(manager.fetchProductOverview());
       gpviewPoduct.setVisible(false);
       gpOverview.setVisible(true);
    }


    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) {
    }

    /**
     *
     * @param products
     * set the ListView for product overview
     * this method is ran at startup!
     */
    private void setListViewContents(HashMap<Integer,Product> products){
        productList = new ArrayList<>();
        productList.addAll(products.values());
        lvProducts.setItems(FXCollections.observableList(productList));

        lvProducts.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

            @Override
            public ListCell<Product> call(ListView<Product> param) {

                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product p, boolean bln) {
                        super.updateItem(p, bln);
                        if (p != null) {
                            setText(p.getShortDescription());
                        }
                    }
                };
            }
        });
    }

    @FXML
    public void lvClickedHandler(MouseEvent mouseEvent) {
        Product p = ((Product) lvProducts.getSelectionModel().getSelectedItem()); // Hvis vi har et produkt her
        System.out.println(p.getProductID());                                       //Hvorfor skal vi så hente det igen
        this.viewProduct(p.getProductID());                   //Især, hvis vi ikke engang kan hente et detailed produkt
    }

    private void viewProduct(int productID) {
        gpviewPoduct.setVisible(true); // Change scene
        gpOverview.setVisible(false);

        DetailedProduct product = manager.fetchProduct(productID); // Fetch product

        //Set values on gui
        labID.setText(String.valueOf(product.getProductID()));
        labName.setText(product.getName());
        tfDesc.setText(product.getShortDescription());

    }
}
