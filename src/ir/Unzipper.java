package ir;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.beans.property.DoubleProperty;

/**
 * This utility extracts files and directories of a standard zip file to a
 * destination directory.
 *
 * @author www.codejava.net
 *
 */
public class Unzipper {

    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    private String DestDirectory = "c:\\testFiles\\unzipTemp";
    int finalSize = 0;
    float currentSize = 0;
    double percentage;
    private DoubleProperty number;
    String zipFilePath;

    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified
     * by destDirectory (will be created if does not exists)
     *
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    
    public Unzipper(String filePath){
        this.zipFilePath = filePath;
    }

    public Unzipper() {
    }
    
    public void unzip(String zipFilePath) throws IOException {
        File destDir = new File(DestDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        finalSize = (int) new File(zipFilePath).length();

        ZipEntry entry = zipIn.getNextEntry();

        // iterates over entries in the zip file
        while (entry != null) {

            currentSize += entry.getCompressedSize();
            double percent = currentSize/finalSize;
            String filePath = destDir + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();

        }
        zipIn.close();
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();

    }
    

    
}
