/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DatabaseHandler;

/**
 *
 * @author Admin
 */
public class Parser {

    public Extractor extractor = new Extractor();
    public FileOperator fileOperator = new FileOperator();
    private String filePath = "C:\\testFiles\\unzipTemp\\";

    public void start() throws IOException {
        
        Path path = Paths.get(this.filePath);
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    parseAndExtract(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            parseAndExtract(path);
        }
    }

    public void parseAndExtract(Path filePath) {
        URI path = filePath.toUri();
        File file = new File(path);
        String fileName = file.getName();
        try {
            String fileType = this.extractor.fileType(file);
//                      c=content , m=metadata , 
            String fileContent = this.extractor.parseFile(file);
            HashMap<String, String> metadataMap = new HashMap<>();
            metadataMap = this.extractor.getMetadata(file);
            
            DatabaseHandler.insertMetadata(metadataMap,fileName,fileType);
            
//            Iterator it = metadataMap.entrySet().iterator();
//            StringBuilder completeMeta = new StringBuilder();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry) it.next();
//                completeMeta.append("\n").append(pair.getKey()).append(" = ").append(pair.getValue());
////                          System.out.println(pair.getKey() + " = " + pair.getValue());
//                it.remove(); // avoids a ConcurrentModificationException
//            }
//                        System.out.println("file name : "+fileName+" "+"file content : "+fileContent+" "+"file type"+fileType);
            this.fileOperator.createFile(fileName, fileContent, fileType);
//                      System.out.println(fileType);
//                      fileOperator.openFile(file);
        } catch (Exception ex) {
            Logger.getLogger(IR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
