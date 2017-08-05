/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

//import ir.AllWork;
//import ir.ProgressBox;
//import ir.Unzipper;
//import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
//import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ProgressController implements Initializable {

    @FXML
    private ProgressBar progressBarCurrent;
    @FXML
    private Button btnCancel;
    @FXML
    private ListView<?> listViewFiles;
    
//    Unzipper unzipper = new Unzipper();
//    AllWork allWork = new AllWork();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        progressBarCurrent.progressProperty().bind(unzipper.getPercentage());

    }

    @FXML
    private void onCancel(ActionEvent event) {
    }

//    public void updateProgress(double percentageCurrent) {
//        double percentage = percentageCurrent;
//        progressBarCurrent.progressProperty().bind();
//    }
    

}
