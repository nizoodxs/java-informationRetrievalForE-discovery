/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.stage.StagePeerListener;
import ir.MAIN;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnlogin;
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
    private void loginAction(ActionEvent event) throws IOException {
//        String inputName = new String();
//        String inputPassword = new String();
//        
//        inputName = txtusername.getText();
//        inputPassword = txtpassword.getText();
//        
//        String userName = new String();
//        String password = new String();
//        String full = new String();
//        Path file = Paths.get("C:\\user.txt");
//        InputStream stream = Files.newInputStream(file);
//        
//        
//        full = file.toString();
//        int index = full.lastIndexOf("+");
//        if (index>=0){
//        userName = full.substring(0,index);
//        password = full.substring(index+1);
//        System.out.println(userName + "\n" + password);
//        }
        Stage window = (Stage) btnlogin.getScene().getWindow();
        window.close();
        MAIN main = new MAIN();
        main.display();
    }
    
}
