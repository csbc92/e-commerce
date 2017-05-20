package ecompim.pimgui;

import java.net.URL;
import java.util.*;

import Product.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ecompim.businessLogic.PIMManager;
import ecompim.businessLogic.IPIM;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;


/**
 * Responsible for managing every interaction between the user and the PIM-system.
 * <p>
 * Initialises the business Logic.
 *
 * @author JV
 */
public class FXMLDocumentController implements Initializable {

    private IPIM manager;
    private HashSet<Category> selectedCategories;

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
    private ListView<String> lvTags;
    @FXML
    private TextField tfAddTag;
    @FXML
    public TableView<Map.Entry<String, String>> tblViewTechDetails;
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
    private TextArea taDesc;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TreeView<Category> categoryTreeView;
    @FXML
    private Button butAddTag;
    @FXML
    private Button butRemoveTag;
    @FXML
    private ListView<Category> lvCategories;
    @FXML
    private ComboBox<Category> cbCategories;
    @FXML
    private Button butAddCategory;
    @FXML
    private TextField tfCategoryName;
    @FXML
    private Button butAddNewCategory;


    /**
     * Initialises the PIM-system, displays the product overview.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = new PIMManager();


        initCategoryOverview();
        gpviewPoduct.setVisible(false);
        gpOverview.setVisible(true);

        setListViewProducts(manager.fetchProductOverview());

    }

    /**
     * Initializes the product category overview
     */
    private void initCategoryOverview(){
        selectedCategories = new HashSet<>();
        Category rootCategory = manager.getRootCategory();
        CheckBoxTreeItem<Category> catItem = this.createCategoryCheckBoxTreeItem(rootCategory, true);
        categoryTreeView.setRoot(catItem);
        categoryTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        catItem.addEventHandler(CheckBoxTreeItem.<Category>checkBoxSelectionChangedEvent(), event -> {
            if (event.getTreeItem().isSelected()) {
                selectedCategories.add(event.getTreeItem().getValue());
            } else {
                selectedCategories.remove(event.getTreeItem().getValue());
            }
            setListViewProducts(manager.fetchProductsByCategory(selectedCategories));
        });
    }

    /**
     * Create a Category CheckBoxTreeItem
     * @param parentCategory Creates the category CheckBoxTreeItem by using this parameter as parent category
     * @return
     */
    private CheckBoxTreeItem<Category> createCategoryCheckBoxTreeItem(Category parentCategory, boolean setExpanded) {
        CheckBoxTreeItem<Category> parentCheckBoxTreeItem = new CheckBoxTreeItem<>(parentCategory);
        parentCheckBoxTreeItem.setExpanded(setExpanded);
        for (Category cat : parentCategory.getChildren()) {
            parentCheckBoxTreeItem.getChildren().add(createCategoryCheckBoxTreeItem(cat, false));
        }
        return parentCheckBoxTreeItem;
    }

    /**
     * NOT DONE
     * Does something when the search button is pressed
     *
     * @param actionEvent
     */
    @FXML
    public void searchButtonHandler(ActionEvent actionEvent) {
        if (searchTextField.getText().isEmpty()) {
            setListViewProducts(manager.fetchProductsByCategory(selectedCategories));
        } else {
            setListViewProducts(manager.searchProducts(searchTextField.getText()));
        }

    }

    /**
     * Fill the ListView for product overview.
     * This method is ran at startup!
     *
     * @param products the products to display
     */
    private void setListViewProducts(HashMap<Integer, Product> products) {
        ArrayList<Product> productList = new ArrayList<>();
        boolean matchFound;
        if (products.isEmpty()) {
            //TODO: Find en mere elegant måde at håndtere ingen resultater.
            lvProducts.getItems().clear();
            lvProducts.getItems().add(new Product(0, "", 0, "Ingen produkter", 0));
            matchFound = false;
        } else {
            productList.addAll(products.values());
            lvProducts.setItems(FXCollections.observableList(productList));
            matchFound = true;
        }
        // Set the CellFactory that is resposible for rendering the data in each TableCell of the ListView
        // Callback<P, R> where P is the argument type and R is the return type of the callback.
        lvProducts.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            // Implementation of the Callback-method that renders the data.
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                // The ListCell<Product> is extended as a new anonymous class and the updateItem-method is overridden.
                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        // super.updateItem(T, boolean) needs to be called to avoid graphical issues.
                        super.updateItem(item, empty);
                        // This is the recommended way to update the ListCell according to Java documentation
                        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html#updateItem-T-boolean-
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            if (matchFound) {
                                setText(item.getName());
                                Double rowHeight = lvProducts.getHeight();
                                String formattedText = String.format("%-5s%-40s kr. %s,-", item.getProductID(), item.getName(), item.getSalePrice());
                                setGraphic(buildTextFlow(formattedText, searchTextField.getText()));
                                setHeight(rowHeight);
                                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                                setText(item.getProductID() + " - " + item.getName());
                            } else {


                                setText(item.getName());
                            }
                        }
                    }
                };
            }
        });
    }

    /**
     * Populates technical details into the specified TableView
     *
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
        if (!techDetailsMap.isEmpty()) {
            ObservableList<Map.Entry<String, String>> technicalDetailsEntries = FXCollections.observableArrayList(techDetailsMap.entrySet());
            tblView.setItems(technicalDetailsEntries);
            // Add the columns to the TableView
            tblView.getColumns().setAll(techProperty, techDescription);
        } else {
            tblView.getColumns().clear();
        }

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
     * <p>
     * Fetches a detailed product from persistence and displays the product information.
     *
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
        populateTechnicalDetails(tblViewTechDetails, manager.getCurrentProduct());
        cbCategories.setItems(FXCollections.observableArrayList(manager.getAllCategories()));

        lvCategories.setItems(populateCategoryListView());

        imgvPic.setImage(manager.fetchMedia(1));
    }
    private ObservableList<Category> populateCategoryListView(){
        ObservableList<Category> allCategories = FXCollections.observableArrayList(manager.getAllCategories());
        ObservableList<Category> productCategories = FXCollections.observableArrayList();
        for(Category cat : allCategories){
            for(Integer id : cat.getProductIDSet()){
                if(id == manager.getCurrentProduct().getProductID()){
                    productCategories.add(cat);
                }
            }
        }
        return productCategories;
    }



    /**
     * Returns to the product overview.
     * <p>
     * Triggered the the "Annuller" button is pressed.
     *
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
        this.initCategoryOverview();
    }

    /**
     * Saves changes to the product currently being modified
     * Returns to the product overview.
     *
     * @param actionEvent
     */
    @FXML
    public void butOkHandler(ActionEvent actionEvent) {
        butApplyHandler(actionEvent);
        butBackHandler(actionEvent);
    }

    /**
     * Saves changes to the product currently being modified
     *
     * @param actionEvent
     */
    @FXML
    public void butApplyHandler(ActionEvent actionEvent) {
        manager.saveChanges();
    }

    /**
     * Discards changes to the product currently being modified and shows the old product information
     *
     * @param actionEvent
     */
    @FXML
    public void butRevertHandler(ActionEvent actionEvent) {
        viewProduct((lvProducts.getSelectionModel().getSelectedItem()).getProductID());
    }

    /**
     * Changes the publicity status of the product currently being modified.
     * Triggered when a radiobutton is pressed
     *
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
     * <p>
     * Triggered when any char is entered in the text area
     *
     * @param keyEvent
     */
    @FXML
    public void taDescOnKeyTypedHandler(KeyEvent keyEvent) {
        String newDescription = taDesc.getText()+keyEvent.getCharacter();
        manager.getCurrentProduct().setLongDescription(newDescription);
    }

    /**
     * Changes the profit margin of the product currently being modified
     * re-calculates the sales price of the product
     * chars that are not digits and not periods ('.') are ignored
     * Triggered when any char is entered in the margin-text field
     *
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
                } catch (NumberFormatException nfe) {
                    System.out.println("Du må ikke have et tal med to punktummer i!");
                }
                tfSalesPrice.setText(String.valueOf(manager.getCurrentProduct().getSalePrice()));
            }
        }
    }


    /**
     * Fetch a fresh list of products from the ERP system and save them to persistance.
     * <p>
     * Overwrites any existing data
     */
    @FXML
    public void menuFetchFromERPHandler(ActionEvent actionEvent) {
        manager.collectERPProducts();
    }

    @FXML
    public void addTagHandler(ActionEvent actionEvent) {
        String tag = tfAddTag.getText();
        if (tag != null) {
            manager.getCurrentProduct().setTag(tag);
            lvTags.setItems(FXCollections.observableList(new ArrayList<>(manager.getCurrentProduct().getTags())));
        }
    }

    @FXML
    public void removeTagHandler(ActionEvent actionEvent) {
        String tag = lvTags.getSelectionModel().getSelectedItem();
        if (tag != null) {
            manager.getCurrentProduct().removeTag(tag);
            lvTags.setItems(FXCollections.observableList(new ArrayList<>(manager.getCurrentProduct().getTags())));
        }
    }
    /**
     * Build TextFlow with selected text. Return "case" dependent.
     *
     * @param text - string with text
     * @param filter - string to select in text
     * @return - TextFlow
     */
    private TextFlow buildTextFlow(String text, String filter) {

        try {
            if (filter.isEmpty()) {
                return new TextFlow(new Text(text));
            }
            //TODO: Error ved søgning af flere ord, som IKKE er i samme rækkefølge
            // Spørg Vinge

            int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
            Text textBefore = new Text(text.substring(0, filterIndex));
            Text textAfter = new Text(text.substring(filterIndex + filter.length()));
            Text textFilter = new Text(text.substring(filterIndex,  filterIndex + filter.length())); //instead of "filter" to keep "case"
            textFilter.setFill(Color.BLACK);
            textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
            return new TextFlow(textBefore, textFilter, textAfter);
        } catch (IndexOutOfBoundsException ie) {

        }
        return null;
    }

    @FXML
    private void butAddCategoryHandler(ActionEvent event) {
        manager.addProductToCategory(cbCategories.getSelectionModel().getSelectedItem().getName());
        lvCategories.setItems(populateCategoryListView());
    }

    @FXML
    private void butAddNewCategoryHandler(ActionEvent event) {
        manager.addNewCategory(tfCategoryName.getText(), cbCategories.getSelectionModel().getSelectedItem().getName());
        for (Category s : manager.getAllCategories()) {
            System.out.println(s.getName());
        }
        cbCategories.setItems(FXCollections.observableArrayList(manager.getAllCategories()));
    }
}
