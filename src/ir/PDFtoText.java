/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
public class PDFtoText {

    public static boolean convertTextToPDF(File file) throws Exception {

        FileInputStream fis = null;
        DataInputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null; 
        String dest = "C:\\extractedFiles\\";

        try {

            Document pdfDoc = new Document();
            String fileName = file.getName();
            int lastIndex = fileName.lastIndexOf(".");
            fileName = fileName.substring(0, lastIndex);
            String output_file = dest +fileName + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(output_file));
            pdfDoc.open();
            pdfDoc.setMarginMirroring(true);
            pdfDoc.setMargins(36, 72, 108, 180);
            pdfDoc.topMargin();
            Font myfont = new Font();
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(10);
            myfont.setStyle(Font.NORMAL);
            myfont.setSize(10);
            pdfDoc.add(new Paragraph("\n"));

            if (file.exists()) {

                fis = new FileInputStream(file);
                in = new DataInputStream(fis);
                isr = new InputStreamReader(in);
                br = new BufferedReader(isr);
                String strLine;

                while ((strLine = br.readLine()) != null) {

                    Paragraph para = new Paragraph(strLine + "\n", myfont);
                    para.setAlignment(Element.ALIGN_JUSTIFIED);
                    pdfDoc.add(para);
                }
            } else {

                System.out.println("no such file exists!");
                return false;
            }
            pdfDoc.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {

            if (br != null) {
                br.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (in != null) {
                in.close();
            }
            if (isr != null) {
                isr.close();
            }

        }

        return true;
    }
}
