/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecompim.PIMGUI;

import java.net.URL;
import java.util.*;


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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * Responsible for managing every interaction between the user and the PIM-system.
 * Initialises the business Logic.
 * @author JV
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ListView lvTags;
    @FXML
    private TextField tfAddTag;
    @FXML
    private ListView lvTechDetails;
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

    /**
     * Initialises the PIM-system, displays the product overview.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = new PIMManager();
        setListViewProducts(manager.fetchProductOverview());
        gpviewPoduct.setVisible(false);
        gpOverview.setVisible(true);
    }


    /**
     * NOT DONE
     * Does something when the search button is pressed
     * @param actionEvent
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) {
    }

    /**
     * Fill the ListView for product overview.
     * This method is ran at startup!
     *
     * @param products the products to display
     *
     *
     */
    private void setListViewProducts(HashMap<Integer, Product> products) {
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

    /**
     * Adds the contents of a collection to a listview
     * @param listView the listView in which to add the products
     * @param col  the collection to add
     */
    private void setListViewStrings(ListView<String> listView, Collection<String> col){
        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.setAll(col);
        listView.setItems(ol);

    }

    /**
     * Changes the "scene" to viewing a product from the product overview
     * Triggered when a product in the listview in the product overview is clicked
     *
     * @param mouseEvent
     */
    @FXML
    public void lvClickedHandler(MouseEvent mouseEvent) {
        this.viewProduct((lvProducts.getSelectionModel().getSelectedItem()).getProductID());
    }

    /**
     * Changes the "scene" to viewing a product from the product overview.
     * Fetches a detailed product from persistence and displays the product information.
     * @param productID
     */
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

        lvTags.setItems(FXCollections.observableList(new ArrayList<>(manager.getCurrentProduct().getTags())));
        tfAddTag.clear();

        setListViewStrings(lvTechDetails,manager.getCurrentProduct().getTechnicalDetails().keySet());

    }

    /**
     * Returns to the product overview.
     * Triggered the the "Annuller" button is pressed.
     * @param actionEvent
     */
    @FXML
    public void butBackHandler(ActionEvent actionEvent) {
        viewOverview();
    }

    /**
     * Returns to the product overview
     */
    private void viewOverview() {
        gpOverview.setVisible(true);
        gpviewPoduct.setVisible(false);
    }

    /**
     * Saves changes to the product currently being modified
     * Returns to the product overview.
     * @param actionEvent
     */
    @FXML
    public void butOkHandler(ActionEvent actionEvent) {
        butApplyHandler(actionEvent);
        butBackHandler(actionEvent);
    }

    /**
     * Saves changes to the product currently being modified
     * @param actionEvent
     */
    @FXML
    public void butApplyHandler(ActionEvent actionEvent) {
        manager.saveChanges();
    }

    /**
     * Discards changes to the product currently being modified and shows the old product information
     * @param actionEvent
     */
    @FXML
    public void butRevertHandler(ActionEvent actionEvent) {
        viewProduct((lvProducts.getSelectionModel().getSelectedItem()).getProductID());
    }


    /**
     * Changes the publicity status of the product currently being modified.
     * Triggered when a radiobutton is pressed
     * @param actionEvent
     */
    @FXML
    public void rbPublicityHandler(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(rbHidden)) {
            manager.getCurrentProduct().setHidden(true);
        } else {
            manager.getCurrentProduct().setHidden(false);
        }
    }

    /**
     * Changes the long description of the product curently being modified
     * Triggered when any char is entered in the text area
     * @param keyEvent
     */
    @FXML
    public void taDescOnKeyTypedHandler(KeyEvent keyEvent) {
        manager.getCurrentProduct().setLongDescription(taDesc.getText());
    }

    /**
     * Changes the profit margin of the product currently being modified
     * re-calculates the sales price of the product
     * chars that are not digits and not periods ('.') are ignored
     *
     * Triggered when any char is entered in the margin-text field
     * @param keyEvent
     */
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

    /**
     * Fetch a fresh list of products from the ERP system and save them to persistance.
     * Overwrites any existing data
     */
    public void menuFetchFromERPHandler(ActionEvent actionEvent) {
        manager.saveERPProducts();
    }

    @FXML
    public void addTagHandler(ActionEvent actionEvent) {
        String tag = tfAddTag.getText();

        if (tag != null) {
            manager.getCurrentProduct().setTag(tag);
            lvTags.setItems(FXCollections.observableList(new ArrayList<>(manager.getCurrentProduct().getTags())));
        }
    }
}
