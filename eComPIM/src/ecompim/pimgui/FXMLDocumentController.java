/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.pimgui;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import ecompim.Product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ecompim.businessLogic.PIMManager;
import ecompim.businessLogic.IPIM;
/**
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {

    private TextField searchTextField;
    private Button searchButton;
    private ListView productListView;
    private TreeView categoryTreeView;
    private IPIM manager;
    private HashMap<Integer,Product> products;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       manager = new PIMManager();
       manager.saveERPProducts();
       products = manager.fetchProductOverview();
       setListViewContents(products);

    }

    public void searchButtonHandler(ActionEvent actionEvent) {
    }

    private void setListViewContents(HashMap<Integer,Product> products){

    }
}
