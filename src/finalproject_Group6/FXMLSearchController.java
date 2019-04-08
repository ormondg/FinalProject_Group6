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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLSearchController implements Initializable {
    
    @FXML
    private Label lblFilter0, lblFilter1, lblFilter2, lblFilter3, lblFilter4, lblFilter5;
    
    @FXML
    private HBox HBoxFilters;
    
    public String [] appliedFilters;
    
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
    
    /*
        swtich scenes to the add patient page
    */
    @FXML
    private void createEmployeeButton(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    public boolean getFilters(String [] filters){
        appliedFilters = filters;
        if (!appliedFilters[2].equals("")){
            try{ // try to turn the number String into real Numbers
                int testNum = Integer.parseInt(appliedFilters[2]); // inputed id
            }catch(NumberFormatException e){ // one is not a number
                return false; // validation failed
            }
        }
        showFilters();
        return true;
    }
    
    private void showFilters(){
        
        for (String appliedFilter : appliedFilters) {  
            if(appliedFilter != null){
                if(!appliedFilter.equals("")){
                    Label label = new Label(appliedFilter);
                    HBoxFilters.getChildren().add(label);
                }
            }
        }
    }
    
    private void showRecords(){
        
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
