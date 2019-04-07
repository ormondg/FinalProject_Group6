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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLSearchController implements Initializable {
    
    /*
        Back to the index button
    */
    @FXML
    private void backToIndex(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
