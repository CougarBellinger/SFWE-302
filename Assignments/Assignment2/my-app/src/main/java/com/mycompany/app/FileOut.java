package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

public class FileOut {

    public static void PDF(String csvFile) throws FileNotFoundException, IOException, DocumentException {
        String file = "CSVtoPDF.pdf";
        PdfDocument pdfDoc = new PdfDocument();

        Document doc = new Document();
        PdfPTable table = new PdfPTable(5);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineStrings = line.split(";");

                for (int i = 1; i < lineStrings.length; ++i) {
                    table.addCell(lineStrings[i]);
                }
            }
        }

        PdfWriter.getInstance(doc, new FileOutputStream(file));

        doc.open();
        doc.add(table);
        doc.close();
    }

    public static void XLS(String csvFile) {

    }
}
