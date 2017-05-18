/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pimgui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import businesslogic.DAMManager;
import businesslogic.IMediaFetcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Vedsted
 */
public class FXMLDocumentController implements Initializable {

    private IMediaFetcher manager;

    @FXML
    private ImageView ivTest;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.manager = new DAMManager();
        ivTest.setImage(manager.fetchMedia("1").getMedia());

      //test  ivTest.setImage(new Image(new File("files/victor.jpg").toURI().toString()));
    }
    
}
