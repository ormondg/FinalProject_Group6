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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private VBox VBoxRecords;
    
    public String [] appliedFilters;
    
    private static ArrayList<Employee> allRecords; // list for all Crew
    private static ArrayList<Employee> allManagers; // list for managers
    private static ArrayList<Employee> allCrewTrainers; // list for crewTrainers
    private static ArrayList<Employee> allCrew; // list for all Crew
    private static Employee editRecord; // list for all Crew
    private static int currentEditRecord = 0;
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
        if (!appliedFilters[0].equals("")){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through records
                if (!allRecords.get(i).getLastName().toLowerCase().contains(appliedFilters[0].toLowerCase())){ // record does not match
                    allRecords.remove(i); // remove record
                    i--; // go back to not miss a record
                }
            }
        }
        
        if (!appliedFilters[1].equals("")){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through records
                if (!allRecords.get(i).getFirstName().toLowerCase().contains(appliedFilters[1].toLowerCase())){ // record does not match
                    allRecords.remove(i); // remove record
                    i--; // go back to not miss a record
                }
            }
        }
        
        if (!appliedFilters[2].equals("")){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through records
                if (!allRecords.get(i).getEmployeeID().contains(appliedFilters[2])){ // record does not match
                    allRecords.remove(i); // remove the record
                    i--; // go back to not miss a record
                }
            }
        }
        
        if (appliedFilters[3] != null){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through records
                if (!allRecords.get(i).getType().equals(appliedFilters[3])){ // records does not match
                    allRecords.remove(i); // remove the record
                    i--; //  go back to not miss a record
                }
            }
        }
        
        if (appliedFilters[4] != null){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through records
                if (!allRecords.get(i).getPayMethod().equals(appliedFilters[4])){ // records does not match
                    allRecords.remove(i); /// remove the record
                    i--; // go back to not miss a record
                }
            }
        }
        
        if (appliedFilters[5] != null){ // filter set
            for (int i = 0; i < allRecords.size(); i ++ ){ // loop through the records
                if (!allRecords.get(i).getIsActive().equals(appliedFilters[5])){ // records does not match
                    allRecords.remove(i); // remove the record
                    i--; // go back to not miss a record
                }
            }
        }

        
        
    }
    
    /*
        Display the records to the FXML document
    */
    private void showRecords(){
        
        sortRecords(); // sort the records first
        
        for (int i = 0; i < allRecords.size(); i ++){ // loop through all the records
                
                Label name = new Label(allRecords.get(i).getFirstName() + " " + allRecords.get(i).getLastName()); // create a label of the name
                Label id = new Label(allRecords.get(i).getEmployeeID()); // create a label of the ID
                Label phone = new Label(allRecords.get(i).getPhone()); // create a label of the phone number
                Label status = new Label(allRecords.get(i).getIsActive()); // create a labal of the active status
                
                // set the labels min/max width
                name.setMinWidth(140);
                id.setMinWidth(140);
                phone.setMinWidth(140);
                status.setMinWidth(140);
                name.setMaxWidth(140);
                id.setMaxWidth(140);
                phone.setMaxWidth(140);
                status.setMaxWidth(140);
                
                //create a eddit button
                Button button = new Button(); // create
                button.setText("Edit"); // set text to edit
                button.setId(Integer.toString(i));
                button.setOnAction((ActionEvent event) -> {
                    Button b = (Button) event.getSource();
                    editRecord = allRecords.get(Integer.parseInt(b.getId()));
                    try {
                        searchForEditRecord();
                    } catch (IOException ex) {
                    }
                });
                
                // create a Hbox and add all the labels
                HBox box = new HBox(); // create
                box.setSpacing(20); // set spacing
                box.getChildren().add(name);
                box.getChildren().add(id);
                box.getChildren().add(phone);
                box.getChildren().add(status);
                box.getChildren().add(button);
                
                //add the hbox to the records vbox in the fxml document
                VBoxRecords.getChildren().add(box);
                
            }
    }
    
    private void searchForEditRecord() throws IOException{
        switch (editRecord.getType()) {
            case "Crew":
                for (int i = 0; i < allCrew.size(); i ++){
                    if (editRecord == allCrew.get(i)){
                        System.out.println(i);
                    }
                }   
                break;
            case "Crew Trainer":
                for (int i = 0; i < allCrewTrainers.size(); i ++){
                    if (editRecord == allCrewTrainers.get(i)){
                        System.out.println(i);
                    }
                }   
                break;
            case "Manager":
                for (int i = 0; i < allManagers.size(); i ++){
                    if (editRecord == allManagers.get(i)){
                        currentEditRecord = i;
                        System.out.println(i);
                    }
                }   
                break;
        }
        Parent set = FXMLLoader.load(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage stage = new Stage(); // create the stage
        stage.setScene(setScene);  // set scene
        stage.show(); // set the stage
        
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                
                
        
            allRecords = new ArrayList(); //setup array list
            allManagers = new ArrayList(); //setup array list
            allCrewTrainers = new ArrayList(); //setup array list
            allCrew = new ArrayList(); //setup array list
            
            
            /*
                First File
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Manager.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allManagers.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
            
            /*
                Second File
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Trainer.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allCrewTrainers.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
            
            /*
                Third file
            */
            try { // get information from the file to file the array
                fi = new FileInputStream("Crew.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allCrew.addAll((ArrayList)oi.readObject()); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            }
            allRecords.addAll(allManagers);
            allRecords.addAll(allCrewTrainers);
            allRecords.addAll(allCrew);
        
    }    
    
}
