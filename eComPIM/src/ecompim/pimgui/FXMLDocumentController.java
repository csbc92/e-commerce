/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.pimgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;

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

/**
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public ListView lvProducts;
    @FXML
    public GridPane gpviewPoduct;
    @FXML
    public GridPane gpOverview;
    @FXML
    public Label labID;
    @FXML
    public Label labName;
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
    public TextArea tfDesc;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TreeView categoryTreeView;

    private IPIM manager;
    private HashMap<Integer,Product> products;
    private ArrayList<Product> productList;
    private ObservableList<Product> oList= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       manager = new PIMManager();
       manager.saveERPProducts();
       products = manager.fetchProductOverview();
       setListViewContents(products);
       gpviewPoduct.setVisible(false);
    }

    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) {
    }

    private void setListViewContents(HashMap<Integer,Product> products){
        productList = new ArrayList<>();
        productList.addAll(products.values());
        oList.setAll(productList);
        lvProducts.setItems(oList);



    }

    @FXML
    public void lvClickedHandler(MouseEvent mouseEvent) {
        int id = ((Product) lvProducts.getSelectionModel().getSelectedItem()).getProductID();
        System.out.println(id);
        this.viewProduct(id);
    }

    private void viewProduct(int productID) {
        gpviewPoduct.setVisible(true);
        gpOverview.setVisible(false);

        Product product = manager.fetchProduct(productID);
        

    }
}
