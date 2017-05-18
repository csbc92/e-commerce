/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pimgui;

import java.net.URL;
import java.util.ResourceBundle;

import businesslogic.DAMManager;
import businesslogic.IMediaFetcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Vedsted
 */
public class FXMLDocumentController implements Initializable {

    private IMediaFetcher manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.manager = new DAMManager();
    }    
    
}
