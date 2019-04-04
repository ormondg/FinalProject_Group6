/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeerecords;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
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
    
    
    @FXML
    private void Find(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void Create(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cmbCategory.getItems().addAll("Full Time","Part Time");
        cmbPayMethod.getItems().addAll("Hourly", "Salary");
        cmbStatus.getItems().addAll("Active", "Inactive");
    }    
    
}
