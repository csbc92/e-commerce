package ecompim.pimgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by victo on 2017-05-19.
 */
public class FXMLPicOverviewPopupController implements Initializable {
    @FXML
    private ListView<ImageView> lvPicOverview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ImageView> picList = new ArrayList<>();
        ObservableList<ImageView> oList = FXCollections.observableArrayList();

        for (int i = 1; i < 10; i++) {
            try {
                ImageView tmp = new ImageView(new Image(FXMLDocumentController.manager.fetchMedia(i).toURI().toString()));
                tmp.setFitHeight(100);
                tmp.setPreserveRatio(true);
                picList.add(tmp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        oList.setAll(picList);
        lvPicOverview.setItems(oList);
    }
}
