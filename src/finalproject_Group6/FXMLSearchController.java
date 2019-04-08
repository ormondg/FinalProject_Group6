/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_Group6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLSearchController implements Initializable {
    
    @FXML
    private HBox HBoxFilters;
    
    @FXML
    private VBox VBoxRecordsName, VBoxRecordsID, VBoxRecordsPhone, VBoxRecordsStatus, VBoxRecordsChange;
    
    public String [] appliedFilters;
    
    private static ArrayList<Employee> allRecords; // list for all Crew
    File file; // create a file reference
    FileOutputStream fo; // create file output reference
    FileInputStream fi; // create file input reference
    ObjectInputStream oi; // create object input reference
    ObjectOutputStream os;  // create object output reference
    
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
        
        for (String appliedFilter : appliedFilters) { // loop through the filters
            if(appliedFilter != null){ // filter is set
                if(!appliedFilter.equals("")){ // filter is set
                    Label label = new Label(appliedFilter); // create a label of the filter
                    HBoxFilters.getChildren().add(label); // add the label to the fxml
                }
            }
        }
        
        showRecords();// show all the records
    }
    
    /*
        Sort through the records removing everything that doesnt match
    */
    private void sortRecords(){
        if (!appliedFilters[0].equals("")){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getLastName().toLowerCase().contains(appliedFilters[0].toLowerCase())){
                    allRecords.remove(i);
                    i--;
                }
            }
        }
        
        if (!appliedFilters[1].equals("")){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getFirstName().toLowerCase().contains(appliedFilters[1].toLowerCase())){
                    allRecords.remove(i);
                    i--;
                }
            }
        }
        
        if (!appliedFilters[2].equals("")){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getEmployeeID().contains(appliedFilters[2])){
                    allRecords.remove(i);
                    i--;
                }
            }
        }
        
        if (appliedFilters[3] != null){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getType().equals(appliedFilters[3])){
                    allRecords.remove(i);
                    i--;
                }
            }
        }
        
        if (appliedFilters[4] != null){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getPayMethod().equals(appliedFilters[4])){
                    allRecords.remove(i);
                    i--;
                }
            }
        }
        
        if (appliedFilters[5] != null){
            for (int i = 0; i < allRecords.size(); i ++ ){
                if (!allRecords.get(i).getIsActive().equals(appliedFilters[5])){
                    allRecords.remove(i);
                    i--;
                }
            }
        }

        
        
    }
    
    /*
        Display the records to the FXML document
    */
    private void showRecords(){
        
        sortRecords();
        
        for (Employee record : allRecords){ // loop through all the records
                Label name = new Label(record.getFirstName() + " " + record.getLastName()); // create a label of the name
                Label id = new Label(record.getEmployeeID()); // create a label of the ID
                Label phone = new Label(record.getPhone()); // create a label of the phone number
                Label status = new Label(record.getIsActive()); // create a labal of the active status
                Button button = new Button();
                button.setText("Edit");
                // add all the labels to the right vbox within the fxml hbox
                VBoxRecordsName.getChildren().add(name);
                VBoxRecordsID.getChildren().add(id);
                VBoxRecordsPhone.getChildren().add(phone);
                VBoxRecordsStatus.getChildren().add(status);
                VBoxRecordsChange.getChildren().add(button);
                
            }
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                
                
        
            allRecords = new ArrayList(); //setup array list
            
            /*
                First File
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Manager.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allRecords.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
            
            /*
                Second File
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Trainer.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allRecords.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
            
            /*
                Third file
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Crew.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allRecords.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
        
    }    
    
}
