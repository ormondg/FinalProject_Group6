/*Author: Graham Ormond, Pawel Babiarz
Final Project Group 6
Thursday, April 11 2019 */
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
import java.util.Collections;
import java.util.Comparator;
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
 * @author Graham Ormond, Pawel Babiarz
 */
public class FXMLSearchController implements Initializable {
    
    @FXML
    private HBox HBoxFilters;
    
    @FXML
    private VBox VBoxRecords;
    
    @FXML
    private Label lblPages;
    
    public String [] appliedFilters;
    
    // arraylist for each file and one for a total record
    private static ArrayList<Employee> allRecords; // list for all Crew
    private static ArrayList<Employee> allManagers; // list for managers
    private static ArrayList<Employee> allCrewTrainers; // list for crewTrainers
    private static ArrayList<Employee> allCrew; // list for all Crew
    
    // used for editing records
    private static Employee editRecord; 
    private static int currentEditRecord = 0;
    
    // reading and writing to a file
    File file; // create a file reference
    FileOutputStream fo; // create file output reference
    FileInputStream fi; // create file input reference
    ObjectInputStream oi; // create object input reference
    ObjectOutputStream os;  // create object output reference
   
    // track the records when showing
    private int recordPage;
    private static final int SHOW_RECORDS = 2;
    
    /*
        Back to the index button
    */
    @FXML
    private void backToIndex(ActionEvent event) throws IOException {
        Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
        setScene.getStylesheets().add(css);
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    /*
        swtich scenes to the add patient page
    */
    @FXML
    private void createEmployeeButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Parent set = (Parent) loader.load(); // load the fxml
        FXMLEmployeeController controller = loader.getController(); // get the second controller
        controller.getFilters(appliedFilters);
        controller.setRecordPage(-2);
        Scene setScene = new Scene(set); // create the scene
        String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
        setScene.getStylesheets().add(css);
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    /*
        Go back a record page
    */
    @FXML
    private void backRecordsButton(ActionEvent event) throws IOException {
        if (recordPage != 0){ // not on first page
            recordPage -= SHOW_RECORDS; // subract the amount per page
            showRecords(); // redisplay the records
        }
    }
    
    /*
        Go farward a record page
    */
    @FXML
    private void nextRecordsButton(ActionEvent event) throws IOException {
        if (recordPage + SHOW_RECORDS < allRecords.size()){ // not the last page
            recordPage += SHOW_RECORDS; // go forwayd the amount of records per page
            showRecords(); //  redisplay the records
        }
    }
    
    /*
        get the filter input from the user
    */
    public boolean getFilters(String [] filters){
        appliedFilters = filters; // get filters
        if (!appliedFilters[2].equals("")){ // id filter set
            try{ // try to turn the input String into real Number
                int testNum = Integer.parseInt(appliedFilters[2]); // inputed id
            }catch(NumberFormatException e){ // input is not a number
                return false; // validation failed
            }
        }
        showFilters(); // display the filters
        return true; // id was a number
    }
    
    /*
        show all the applied filters
    */
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

        // compare the employees by id
        Comparator<Employee> compareById = (Employee e1, Employee e2) -> {
            Integer id1 = Integer.parseInt(e1.getEmployeeID());
            Integer id2 = Integer.parseInt(e2.getEmployeeID());
            return id1.compareTo(id2);
        };
        Collections.sort(allRecords, compareById); // sort the employees by id
    }
    
    /*
        Display the records to the FXML document
    */
    private void showRecords(){
        
        sortRecords(); // sort the records first
        VBoxRecords.getChildren().clear();// clear all the records
        int totalPages = (allRecords.size()/SHOW_RECORDS); // get the total full pages
        int page = (recordPage/SHOW_RECORDS); // get the page the user is veiwing
        if (allRecords.isEmpty()){ // no records
            page = 0; // no pages
        }
        else{
            page ++; // one page for remaineres
        }
        
        if(allRecords.size()%SHOW_RECORDS != 0){ // half a page is there
            totalPages ++; // add a page to the total
        }
        lblPages.setText("Page: " + page + " of " + totalPages); // set the pages label
        
        // no records found
        if (allRecords.isEmpty()){ // empty list
            // create and show a label
            Label label = new Label("No Records Found");
            VBoxRecords.getChildren().add(label);
        }else{ // display the records
            // run until record size or endrecord = 0 for page cut off
            int endRecord = SHOW_RECORDS ; // go farward 15 records counter
            for (int i = recordPage; i < allRecords.size(); i ++){ // loop through all the records
                    endRecord --; // down one record on the counter
                    Label name = new Label(allRecords.get(i).getFirstName() + " " + allRecords.get(i).getLastName()); // create a label of the name
                    Label id = new Label(allRecords.get(i).getEmployeeID()); // create a label of the ID
                    Label phone = new Label(allRecords.get(i).getPhone()); // create a label of the phone number
                    Label status = new Label(allRecords.get(i).getIsActive()); // create a labal of the active status

                    // set the labels min/max width
                    name.setMinWidth(200);
                    name.setMaxWidth(200);
                    id.setMinWidth(145);
                    id.setMaxWidth(145);
                    phone.setMinWidth(170);
                    phone.setMaxWidth(170);
                    status.setMinWidth(140);
                    status.setMaxWidth(140);

                    //create a eddit button
                    Button button = new Button(); // create
                    button.setText("Edit"); // set text to edit
                    button.setId(Integer.toString(i)); // give the button the id of the array index
                    // create on action method
                    button.setOnAction((ActionEvent event) -> {
                        Button b = (Button) event.getSource();  // get the button event
                        editRecord = allRecords.get(Integer.parseInt(b.getId())); // get the buttons id
                        try {
                            searchForEditRecord(event);
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

                    // done showing max number of records per page
                    if (endRecord == 0){
                        break; // stop showing
                    }

                }
        }
    }
    
    /*
        search for a record to edit
    */
    private void searchForEditRecord(ActionEvent event) throws IOException{
        // search for the file type
        switch (editRecord.getType()) {
            case "Crew": // crew file
                for (int i = 0; i < allCrew.size(); i ++){ //  loop through the file
                    if (editRecord == allCrew.get(i)){ // get the record
                        currentEditRecord = i; // create a index reference
                    }
                }   
                break;
            case "Crew Trainer": // crew trainer file
                for (int i = 0; i < allCrewTrainers.size(); i ++){ // loop through file
                    if (editRecord == allCrewTrainers.get(i)){ // get the record
                        currentEditRecord = i; // create a index
                    }
                }   
                break;
            case "Manager": // manager file
                for (int i = 0; i < allManagers.size(); i ++){ // loop through file
                    if (editRecord == allManagers.get(i)){ // get the record
                        currentEditRecord = i; // create a index
                    }
                }   
                break;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEmployee.fxml")); // get FXML
        Parent set = (Parent) loader.load(); // load the fxml
        FXMLEmployeeController controller = loader.getController(); // get the second controller
        controller.getFilters(appliedFilters);
        controller.editEmployeeInformation(editRecord, currentEditRecord);
        Scene setScene = new Scene(set); // create the scene
        String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
        setScene.getStylesheets().add(css);
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
        
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
            
            editRecord = new Employee();
            
            recordPage = 0; // put the user on the first page
            
            
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
            
            // create an array list with all the records
            allRecords.addAll(allManagers);
            allRecords.addAll(allCrewTrainers);
            allRecords.addAll(allCrew);
        
    }    
    
}
