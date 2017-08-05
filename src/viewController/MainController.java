/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import com.jfoenix.controls.JFXButton;
import ir.AllWork;
import ir.ProgressBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MainController implements Initializable {
    
    private String filePath = "";

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b21;
    @FXML
    private JFXButton b22;
    @FXML
    private ListView<String> mylistview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onHome(ActionEvent event) {
    }

    @FXML
    private void onSearch(ActionEvent event) {
    }

    @FXML
    private void onHelp(ActionEvent event) {
    }

    @FXML
    private void onLogout(ActionEvent event) {
    }

    @FXML
    private void onExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addfiles(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ZIP files","*.zip"));
        File selectedFile=fc.showOpenDialog(null);
        filePath = selectedFile.getAbsolutePath();
        if(selectedFile !=null){
            mylistview.getItems().add(selectedFile.getName());
        }
        else {
        System.out.println("File is not valid");
        }
    }

    @FXML
    private void unzipitems(ActionEvent event) throws IOException {
        if(!filePath.isEmpty()){
            AllWork allWork = new AllWork(filePath);
            allWork.startWork();
            
        }
    }
    
}
