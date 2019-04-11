/*Author: Graham Ormond, Pawel Babiarz
Final Project Group 6
Thursday, April 11 2019 */
package finalproject_Group6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Graham Ormond, Pawel Babiarz
 */
public class FinalProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLIndex.fxml")); // load index page to start
        
        Scene scene = new Scene(root);
        String css = FinalProject.class.getResource("EmployeeCSS.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
