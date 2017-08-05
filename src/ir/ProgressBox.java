/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class ProgressBox {

    ProgressBar progressBar = new ProgressBar();
    Stage window = new Stage();

    public void display() throws IOException {

        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/view/progress.fxml"));

        this.window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        this.progressBar = (ProgressBar) scene.lookup("currentBarProgress");

        this.window.setScene(scene);
//        this.window.showAndWait();

    }

    public void activateProgressBar(final Task<?> task) throws IOException {

        this.progressBar.progressProperty().bind(task.progressProperty());
        display();
    }

    public Stage getDialogStage() {
        return this.window;
    }
    
    public void close(){
        this.window.close();
        Alert.display("Alert","Task Completed succesfully");
    }

}
