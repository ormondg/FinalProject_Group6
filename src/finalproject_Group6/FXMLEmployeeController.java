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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class FXMLEmployeeController implements Initializable {
    
    @FXML
    private TextField txtID, txtFirstName, txtLastName, txtJobTitle, txtHireDate,
            txtEndDate, txtBirthDate, txtPhone, txtEmail, txtAddres, txtSIN,
            txtPayRate;
    
    @FXML
    private Button btnCreate;
    
    @FXML
    private Label lblError, lblStatusShow;
    
    @FXML
    ComboBox cmbGender, cmbPayMethod, cmbCategory;
    
    private static ArrayList<Employee> allManagers; // list for managers
    private static ArrayList<Employee> allCrewTrainers; // list for crewTrainers
    private static ArrayList<Employee> allCrew; // list for all Crew
    private static Employee currentEmployee;
    private static int currentRecord;
    private static String fileReference;
    
    
    File file; // create a file reference
    FileOutputStream fo; // create file output reference
    FileInputStream fi; // create file input reference
    ObjectInputStream oi; // create object input reference
    ObjectOutputStream os;  // create object output reference
    
    /*
        Create an Employee
    
    */
    @FXML
    private void createEmployeeButton(ActionEvent event) throws IOException {
        
        // try to create an employee
        if(createEmployee()){ // sucessful == move on to next page
            Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
            Scene setScene = new Scene(set); // create the scene
            Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
            setStage.setScene(setScene);  // set scene
            setStage.show(); // set the stage
        }else{ // stay on page (record not created)
            
        }
    }
    
    /*
        cancel Creating a employee
    */
    @FXML
    private void cancelToIndex(ActionEvent event) throws IOException {
        // go back to the index page
        Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
        Scene setScene = new Scene(set); // create the scene
        Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
        setStage.setScene(setScene);  // set scene
        setStage.show(); // set the stage
    }
    
    private boolean createEmployee() throws FileNotFoundException, IOException{
        Employee e = new Employee();// create an employee reference
        // take all the info form the form into the employee reference
        e.setLastName(txtLastName.getText());
        e.setFirstName(txtFirstName.getText());  
        e.setBirthDate(txtBirthDate.getText());
        e.setGender((String) cmbGender.getValue()); 
        e.setPhone(txtPhone.getText()); 
        e.setEmail(txtEmail.getText()); 
        e.setAddress(txtAddres.getText()); 
        e.setHireDate(txtHireDate.getText()); 
        e.setSIN(txtSIN.getText()); 
        e.setEmployeeID(txtID.getText()); 
        e.setType((String) cmbCategory.getValue()); 
        e.setPayMethod((String) cmbPayMethod.getValue());   
        e.setRateOfPay(txtPayRate.getText()); 
        
        if (e.validate()){ // vaidate the employee
            lblError.setText("");
            if (currentRecord != -1){
                deleteEmployeeRecord();
            }
            createNewEmployee(e);
            return true; // employee created sucessfuly
        }else{ // false validation
            lblError.setText(e.errors); // show the error
            return false; // return the an employee cannot be create
        }
    }
    
    public void createNewEmployee(Employee e) throws FileNotFoundException, IOException{
        switch (e.getType()) {
                case "Manager": // write to the manager file
                    allManagers.add(allManagers.size(), e);  // add the crew refence to the end of the array
                    fo = new FileOutputStream("Manager.bat"); // get file 
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allManagers); // write object
                    os.close(); // close file
                    break;
                case "Crew Trainer": // write to the crew trainer file
                    allCrewTrainers.add(allCrewTrainers.size(), e);  // add the crew refence to the end of the array
                    fo = new FileOutputStream("Trainer.bat");
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrewTrainers);
                    os.close();
                    break;
                case "Crew": // write to the general crew file
                    allCrew.add(allCrew.size(), e);  // add the crew refence to the end of the array
                    fo = new FileOutputStream("Crew.bat"); // get file 
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrew); // write object
                    os.close(); // close file
                    break;
        }
        
        // re wright the last file the record was in
        if (!currentEmployee.getEmployeeID().equals("-1")){ // editing a record
            switch (currentEmployee.getType()) {
                case "Manager": // write to the manager file
                    fo = new FileOutputStream("Manager.bat"); // get file 
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allManagers); // write object
                    os.close(); // close file
                    break;
                case "Crew Trainer": // write to the crew trainer file
                    fo = new FileOutputStream("Trainer.bat");
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrewTrainers);
                    os.close();
                    break;
                case "Crew": // write to the general crew file
                    fo = new FileOutputStream("Crew.bat"); // get file 
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrew); // write object
                    os.close(); // close file
                    break;
            }
        }
    }
    
    /*
        setup editing an employee record
    */
    public void editEmployeeInformation(Employee e, int recordRef){
        currentEmployee = e; // get the record instance
        currentRecord = recordRef; // get the record reference
        
        // set the textfeilds
        txtLastName.setText(e.getLastName()); 
        txtFirstName.setText(e.getFirstName());
        txtBirthDate.setText(e.getBirthDate());
        cmbGender.setValue(e.getGender());
        txtPhone.setText(e.getPhone());
        txtEmail.setText(e.getEmail());
        txtAddres.setText(e.getAddress());
        txtHireDate.setText(e.getHireDate());
        txtSIN.setText(e.getSIN());
        txtID.setText(e.getEmployeeID());
        cmbCategory.setValue(e.getType());
        cmbPayMethod.setValue(e.getPayMethod());
        txtPayRate.setText(e.getRateOfPay());
        lblStatusShow.setText(e.getIsActive());
        btnCreate.setText("Update"); // change the button name
    }
    
    /*
        deletes an employee record
    */
    public void deleteEmployeeRecord(){
        switch (currentEmployee.getType()) {
                case "Manager": // write to the manager file
                    allManagers.remove(currentRecord);
                    break;
                case "Crew Trainer": // write to the crew trainer file
                    allCrewTrainers.remove(currentRecord);
                    break;
                case "Crew": // write to the general crew file
                    allCrew.remove(currentRecord);
                    break;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // setup combo boxes
        cmbGender.getItems().addAll("Male","Female"); 
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbCategory.getItems().addAll("Crew","Crew Trainer", "Manager");
        
        currentRecord = -1;
        currentEmployee = new Employee();
        fileReference = "";
        
        
        /*
            Used for object writing to file
        */
        allManagers = new ArrayList(); //setup array list
        allCrewTrainers = new ArrayList(); //setup array list
        allCrew = new ArrayList(); //setup array list
        /*
            First File
        */
        try { // get information from the file to file the array
            fi = new FileInputStream("Manager.bat") ; // get file
            oi = new ObjectInputStream(fi); // get object from file
            allManagers = (ArrayList)oi.readObject(); // add it to the array list
        } catch (FileNotFoundException ex) { // no file or info
        } catch (IOException | ClassNotFoundException ex) { // no file or info
        }

        /*
            Second File
        */
        try { // get information from the file to file the array
            fi = new FileInputStream("Trainer.bat") ; // get file
            oi = new ObjectInputStream(fi); // get object from file
            allCrewTrainers = (ArrayList)oi.readObject(); // add it to the array list
        } catch (FileNotFoundException ex) { // no file or info
        } catch (IOException | ClassNotFoundException ex) { // no file or info
        }

        /*
            Third file
        */
        try { // get information from the file to file the array
            fi = new FileInputStream("Crew.bat") ; // get file
            oi = new ObjectInputStream(fi); // get object from file
            allCrew = (ArrayList)oi.readObject(); // add it to the array list
        } catch (FileNotFoundException ex) { // no file or info
        } catch (IOException | ClassNotFoundException ex) { // no file or info
        }
    }
    
}
