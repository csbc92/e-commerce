package damgui;


import businesslogic.DAMManager;
import businesslogic.IMediaFetcher;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLDocumentController implements Initializable {

    private IMediaFetcher manager;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        this.manager = new DAMManager();
    }

}



