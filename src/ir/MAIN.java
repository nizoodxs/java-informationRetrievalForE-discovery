/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class MAIN {
    
    public void display() throws IOException{
        
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        
        Scene scene = new Scene(root);
        
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
    
    }
}
