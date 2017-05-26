package pimgui;


import java.net.URL;
import java.util.ResourceBundle;
import businesslogic.DAMManager;
import businesslogic.IMediaFetcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Vedsted
 */

public class FXMLDocumentController implements Initializable {

    private IMediaFetcher manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.manager = new DAMManager();
    }


}

