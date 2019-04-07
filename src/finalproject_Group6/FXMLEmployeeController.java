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
    private TextField txtId, txtFirstName, txtLastName, txtJobTitle, txtHireDate,
            txtEndDate, txtBirthDate, txtPhone, txtEmail, txtAddres, txtSIN, txtID,
            txtPayRate;
    
    @FXML
    private Label lblError;
    
    @FXML
    ComboBox cmbGender, cmbPayMethod, cmbCategory;
    
    private static ArrayList<Employee> allManagers;
    private static ArrayList<Employee> allCrewTrainers;
    private static ArrayList<Employee> allCrew;
    File file;
    FileOutputStream fo;
    FileInputStream fi;
    ObjectInputStream oi;
    ObjectOutputStream os; 
    /*
    
        Create an Employee
    
    */
    @FXML
    private void createEmployeeButton(ActionEvent event) throws IOException {
        
        if(createEmployee()){
            Parent set = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // get FXML
            Scene setScene = new Scene(set); // create the scene
            Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // create the stage
            setStage.setScene(setScene);  // set scene
            setStage.show(); // set the stage
        }else{
            
        }
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
    
    private boolean createEmployee() throws FileNotFoundException, IOException{
        Employee e = new Employee();
        // take all the info form the form
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
        
        if (e.validate()){
            lblError.setText("");
            switch (e.getType()) {
                case "Manager":
                    allManagers.add(allManagers.size(), e);  // add the manager refence to the end of the array
                    fo = new FileOutputStream("Manager.bat");
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allManagers);
                    os.close();
                    break;
                case "Crew Trainer":
                    allCrewTrainers.add(allCrewTrainers.size(), e);  // add the manager refence to the end of the array
                    fo = new FileOutputStream("Trainer.bat");
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrewTrainers);
                    os.close();
                    break;
                default:
                    allCrew.add(allCrew.size(), e);  // add the manager refence to the end of the array
                    fo = new FileOutputStream("Crew.bat");
                    os = new ObjectOutputStream(fo);
                    os.writeObject(allCrew);
                    os.close();
                    break;
            }
                        /*
                    used for writing objects to file
                */
            return true;
        }else{
            lblError.setText(e.errors);
            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbGender.getItems().addAll("Male","Female");
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbCategory.getItems().addAll("Crew","Crew Trainer", "Manager");
        
        /*
            Used for object writing to file
        */
        allManagers = new ArrayList(); //setup array list
        allCrewTrainers = new ArrayList(); //setup array list
        allCrew = new ArrayList(); //setup array list
        if(allManagers.isEmpty() || allCrewTrainers.isEmpty() || allCrew.isEmpty()){ // if the array list is empty
            try { // get information from the file to file the array
                fi = new FileInputStream("Manager.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allManagers = (ArrayList)oi.readObject(); // add it to the array list
                
                fi = new FileInputStream("Trainer.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allCrewTrainers = (ArrayList)oi.readObject(); // add it to the array list
                
                fi = new FileInputStream("Crew.bat") ; // get file
                oi = new ObjectInputStream(fi); // get object from file
                allCrew = (ArrayList)oi.readObject(); // add it to the array list
            } catch (FileNotFoundException ex) { // no file or info
            } catch (IOException | ClassNotFoundException ex) { // no file or info
            } 
        }  
    }    
    
}
