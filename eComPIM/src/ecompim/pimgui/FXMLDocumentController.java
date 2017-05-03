/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.pimgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


import ecompim.Product.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ecompim.businessLogic.PIMManager;
import ecompim.businessLogic.IPIM;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
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
    public TextField tfCostPrice;
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
    private TextArea taDesc;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TreeView categoryTreeView;

    private IPIM manager;

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
     * @param products set the ListView for product overview
     *                 this method is ran at startup!
     */
    private void setListViewContents(HashMap<Integer, Product> products) {
        ArrayList<Product> productList = new ArrayList<>();
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
        this.viewProduct((lvProducts.getSelectionModel().getSelectedItem()).getProductID());
    }

    private void viewProduct(int productID) {
        gpviewPoduct.setVisible(true); // Change scene
        gpOverview.setVisible(false);

        manager.setCurrentProduct(productID);// sets currentProduct in manager

        //Set values on gui
        labID.setText(String.valueOf(manager.getCurrentProduct().getProductID()));
        labName.setText(manager.getCurrentProduct().getName());
        taDesc.setText(manager.getCurrentProduct().getLongDescription());

        if (manager.getCurrentProduct().isHidden()) {
            rbHidden.setSelected(true);
        } else {
            rbPublic.setSelected(true);
        }

        tfCostPrice.setText(String.valueOf(manager.getCurrentProduct().getCostPrice()));
        tfMargin.setText(String.valueOf(manager.getCurrentProduct().getMargin()));
        tfSalesPrice.setText(String.valueOf(manager.getCurrentProduct().getSalePrice()));

    }

    @FXML
    public void butBackHandler(ActionEvent actionEvent) {
        viewOverview();
    }

    private void viewOverview() {
        gpOverview.setVisible(true);
        gpviewPoduct.setVisible(false);
    }

    @FXML
    public void butOkHandler(ActionEvent actionEvent) {
        butApplyHandler(actionEvent);
        butBackHandler(actionEvent);
    }

    @FXML
    public void butApplyHandler(ActionEvent actionEvent) {
        manager.saveChanges();
    }

    @FXML
    public void butRevertHandler(ActionEvent actionEvent) {
        viewProduct((lvProducts.getSelectionModel().getSelectedItem()).getProductID());
    }

    @FXML
    public void tfMarginHandler(ActionEvent actionEvent) {

    }

    @FXML
    public void rbPublicityHandler(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(rbHidden)) {
            manager.getCurrentProduct().setHidden(true);
        } else {
            manager.getCurrentProduct().setHidden(false);
        }
    }

    @FXML
    public void taDescOnKeyTypedHandler(KeyEvent keyEvent) {
        manager.getCurrentProduct().setLongDescription(taDesc.getText());
    }

    @FXML
    public void tfMarginOnKeyTypedHandler(KeyEvent keyEvent) {

        if (tfMargin.getText().isEmpty()) {
            manager.getCurrentProduct().setMargin(0);
            tfSalesPrice.setText(String.valueOf(manager.getCurrentProduct().getSalePrice()));

        } else {
            if (!Character.isDigit(keyEvent.getCharacter().charAt(0)) && !(keyEvent.getCharacter().charAt(0) == '.')) {
  
                keyEvent.consume();
                manager.getCurrentProduct().setMargin(Double.parseDouble(tfMargin.getText()));
            } else {
                try {
                    manager.getCurrentProduct().setMargin(Double.parseDouble(tfMargin.getText() + keyEvent.getCharacter()));
                } catch(NumberFormatException nfe) {
                    System.out.println("Du må ikke have et tal med to punktummer i!");
                }
                tfSalesPrice.setText(String.valueOf(manager.getCurrentProduct().getSalePrice()));
            }
        }
    }
}
