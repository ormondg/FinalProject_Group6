/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_Group6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Graham
 */
public class FXMLIndexController implements Initializable {
    
    /*
        swtich scenes to the add patient page
    */
    @FXML
    private void showAddEmployeePage(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    /*
        Switch scenes to the record page
    */
    @FXML
    private void showSearchPage(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLSearch.fxml")); // get FXML
        Scene setScene = new Scene(set); // create scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create stage
        setStage.setScene(setScene); // set scene
        setStage.show(); // set stage
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
