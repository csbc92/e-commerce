package ecompim.pimgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by victo on 2017-05-19.
 */
public class FXMLPicOverviewPopupController implements Initializable {
    @FXML
    private ListView lvPicOverview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> picList = new ArrayList<>();
        ObservableList<String> oList = FXCollections.observableArrayList();

        for (int i = 1; i < 10; i++) {
            try {
                picList.add(FXMLDocumentController.manager.fetchMedia(i).toURI().toString());
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
