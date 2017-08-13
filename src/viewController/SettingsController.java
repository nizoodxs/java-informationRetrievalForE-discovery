/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SettingsController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnsave;
    @FXML
    private Label titleLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveClick(ActionEvent event) throws IOException {
        String userName = txtusername.getText();
        String password = txtpassword.getText();
        String full = userName+"+"+password;
        File file = new File("C:\\user.txt");
        BufferedWriter outStream = new BufferedWriter(new FileWriter(file));
        outStream.write(full);  //Replace with the string you are trying to write  
        outStream.close();
    }

}
