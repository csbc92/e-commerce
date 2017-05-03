/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomweb;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    private Label label;
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
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
