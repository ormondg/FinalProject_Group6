/*Author: Graham Ormond, Pawel Babiarz
Final Project Group 6
Thursday, April 11 2019 */
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * 
 * @author Graham Ormond, Pawel Babiarz
 */
public class FXMLIndexController implements Initializable {
    
    @FXML
    private TextField txtId, txtFirstName, txtLastName;

    @FXML
    private ComboBox cmbCategory, cmbPayMethod, cmbStatus;
    
    @FXML
    private Label lblError;
    
    public String [] appliedFilters = new String[6];

    
    
    /*
        swtich scenes to the add patient page
    */
    @FXML
    private void createEmployeeButton(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
        setScene.getStylesheets().add(css);
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    /*
        Button to the veiwing of records
    */
    @FXML
    private void showRecordPage(ActionEvent event) throws IOException {
        getFilterInput();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSearch.fxml")); // get FXML
        Parent set = (Parent) loader.load(); // load the fxml
        FXMLSearchController controllerTwo = loader.getController(); // get the second controller
        if(controllerTwo.getFilters(appliedFilters)){// give a method the filters to save them
            Scene setScene = new Scene(set); // create the scene
            Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
            String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
            setScene.getStylesheets().add(css);
            setStage.setScene(setScene);  // set scene
            setStage.show(); // set the stage
        }else{
            lblError.setText("ID must be a number");
        } 
    }
    
    /*
        get the users filter input
    */
    public void getFilterInput(){
        //setup filter array
        appliedFilters[0] = txtLastName.getText();
        appliedFilters[1] = txtFirstName.getText();
        appliedFilters[2] = txtId.getText();
        appliedFilters[3] = (String) cmbCategory.getValue();
        appliedFilters[4] = (String) cmbPayMethod.getValue();
        appliedFilters[5] = (String) cmbStatus.getValue();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // setup combo boxes
        cmbCategory.getItems().addAll("Crew","Crew Trainer", "Manager");
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbStatus.getItems().addAll("Active", "Inactive");
    }    
    
}

