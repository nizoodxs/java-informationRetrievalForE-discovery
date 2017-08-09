/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ir.Alert;
import ir.AllWork;
import ir.PDFtoText;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.lucene.queryparser.classic.ParseException;
import ir.Searcher;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.control.Button;

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
    @FXML
    private JFXTextField searchArea;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private ListView<String> searchList;
    @FXML
    private Button btnExtract;

    ArrayList<String> resultList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onHome(ActionEvent event) throws IOException {
        Stage window = (Stage) btnSearch.getScene().getWindow();
        window.close();
        loadWindow("/view/main.fxml");
    }

    @FXML
    private void onSearch(ActionEvent event) {
    }

    @FXML
    private void onHelp(ActionEvent event) throws IOException {
        loadWindow2("/view/help.fxml");
    }

    @FXML
    private void onLogout(ActionEvent event) throws IOException {
          Stage window = (Stage) mylistview.getScene().getWindow();
          window.close();
          loadWindow("/view/login.fxml");
    }

    @FXML
    private void onExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addfiles(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ZIP files", "*.zip"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            mylistview.getItems().add(selectedFile.getName());
        } else {
            System.out.println("File is not valid");
        }
    }

    @FXML
    private void unzipitems(ActionEvent event) throws IOException {
        if (!filePath.isEmpty()) {
            AllWork allWork = new AllWork(filePath);
            allWork.startWork();

        }
    }

    @FXML
    private void onSearchPressed(ActionEvent event) throws IOException, ParseException {
        String query = searchArea.getText();

        if (query != null) {

            ArrayList<String> gotList = new ArrayList<>();

            gotList = Searcher.search(query);
            this.resultList.addAll(gotList);

            for (Iterator<String> iterator = gotList.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                int startIndex = next.lastIndexOf("\\") + 1;
                String name = next.substring(startIndex);
                searchList.getItems().add(name + " : " + next);
            }
        }
//        SearchController searchController = new SearchController();
//        searchController.display(query);
//        loadWindow("/view/search.fxml");
//        DisplaySearch sSearch = new DisplaySearch();
//        sSearch.display(query);
    }

    private void loadWindow(String loc) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(loc));

        Scene scene = new Scene(root);

        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();

    }
        private void loadWindow2(String loc) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(loc));

        Scene scene = new Scene(root);

        window.initStyle(StageStyle.UTILITY);
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void onClickExtract(ActionEvent event) throws Exception {
        for (Iterator<String> iterator = this.resultList.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            File file = new File(next);
            PDFtoText.convertTextToPDF(file);
        }

        Alert.display("Completed", "Extraction completed at location C:\\extractedFiles");
    }

}
