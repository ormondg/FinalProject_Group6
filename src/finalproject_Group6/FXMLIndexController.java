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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLIndexController implements Initializable {
    
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    
    @FXML
    private ComboBox cmbCategory;
    @FXML
    private ComboBox cmbPayMethod;
    @FXML
    private ComboBox cmbStatus;
    
    @FXML
    private TextField txtJobTitle;
    @FXML
    private TextField txtHireDate;
    @FXML
    private TextField txtEndDate;
    
    
    
    @FXML
    private Button btnFind;
    
    @FXML
    private Button btnCreate;
    
    
    /*
        swtich scenes to the add patient page
    */
    @FXML
    private void showEmployeePage(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    @FXML
    private void showRecordPage(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLSearch.fxml")); // get FXML
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
        cmbCategory.getItems().addAll("Full Time","Part Time");
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbStatus.getItems().addAll("Active", "Inactive");
    }    
    
}

