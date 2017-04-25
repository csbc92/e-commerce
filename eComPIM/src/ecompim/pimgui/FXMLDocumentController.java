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
/**
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private ListView productListView;
    @FXML
    private TreeView categoryTreeView;

    private IPIM manager;
    private HashMap<Integer,Product> products;
    private ArrayList<String> nameList;
    private ObservableList<String> oList= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       manager = new PIMManager();
       manager.saveERPProducts();
       products = manager.fetchProductOverview();
       setListViewContents(products);
    }

    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) {
    }

    private void setListViewContents(HashMap<Integer,Product> products){
        nameList = new ArrayList<>();
        for (Product p: products.values()
                ) {
            nameList.add(p.getProductName());
        }
        oList.setAll(nameList);
        productListView.setItems(oList);



    }
}
