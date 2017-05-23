package ecompim.pimgui;

import Product.IDisplayable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by victo on 2017-05-19.
 */
public class FXMLPicOverviewPopupController implements Initializable {
    @FXML
    private ListView<IDisplayable> lvPicOverview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<IDisplayable> picList = new ArrayList<>();
        ObservableList<IDisplayable> oList = FXCollections.observableArrayList();

        for (int i = 1; i < 10; i++) {
            try {
                IDisplayable temp = FXMLDocumentController.manager.fetchMedia(i);

                if (temp.getID() != 0) {
                    picList.add(temp);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        lvPicOverview.setCellFactory(new Callback<ListView<IDisplayable>, ListCell<IDisplayable>>() {
            // Implementation of the Callback-method that renders the data.
            @Override
            public ListCell<IDisplayable> call(ListView<IDisplayable> param) {
                // The ListCell<IDisplayable> is extended as a new anonymous class and the updateItem-method is overridden.
                return new ListCell<IDisplayable>() {

                    private final ImageView iv = new ImageView();

                    @Override
                    protected void updateItem(IDisplayable item, boolean empty) {
                        // super.updateItem(T, boolean) needs to be called to avoid graphical issues.
                        super.updateItem(item, empty);
                        // This is the recommended way to update the ListCell according to Java documentation
                        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html#updateItem-T-boolean-
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            iv.setImage(new Image(new File(item.getPath()).toURI().toString()));
                            iv.setFitHeight(100);
                            iv.setPreserveRatio(true);
                            setGraphic(iv);
                        }
                    }
                };
            }
        });



        oList.setAll(picList);
        lvPicOverview.setItems(oList);
    }

    @FXML
    private void butChangePicHandler(ActionEvent actionEvent) {
        IDisplayable tmp = lvPicOverview.getSelectionModel().getSelectedItem();
        if (tmp != null) {
            FXMLDocumentController.manager.getCurrentProduct().addMedia(tmp);
        }
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLDocumentController.cont.updateImage();
    }
}
