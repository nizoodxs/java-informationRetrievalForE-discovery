/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.io.IOException;
import javafx.concurrent.Task;

/**
 *
 * @author Admin
 */
public class AllWork {

    Unzipper unzipper = new Unzipper();
    Parser parser = new Parser();
    String zipFilePath = new String();
    String parsingPath = "C:\\testFiles\\unzipTemp";

    public AllWork(String filePath) {
        zipFilePath = filePath;
    }

    public void startWork() throws IOException {

        ProgressBox progressBox = new ProgressBox();

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException, IOException {

                unzipper.unzip(zipFilePath);
                parser.start();
                Indexer.index();
                
                for (int i = 0; i < 10; i++) {
                    updateProgress(i, 10);
                    Thread.sleep(200);
                }
                updateProgress(10, 10);
                FileOperator.deleteTempZip();
                return null;
            }
        };

        // binds progress of progress bars to progress of task:
        progressBox.activateProgressBar(task);

        // in real life this method would get the result of the task
        // and update the UI based on its value:
        task.setOnSucceeded(event -> {
            progressBox.close();
        });

        progressBox.getDialogStage().show();

        Thread thread = new Thread(task);
        thread.start();
    }
}
