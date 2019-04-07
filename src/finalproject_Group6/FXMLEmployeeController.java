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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLEmployeeController implements Initializable {
    
    @FXML
    ComboBox cmbGender, cmbPayMethod, cmbCategory;
    /*
    
        Create an Employee
    
    */
    @FXML
    private void createEmployee(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    /*
        cancel Creating a employee
    */
    @FXML
    private void cancelToIndex(ActionEvent event) throws IOException {
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
        cmbGender.getItems().addAll("Male","Female");
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbCategory.getItems().addAll("Crew","Crew Trainer", "Manager");
    }    
    
}
