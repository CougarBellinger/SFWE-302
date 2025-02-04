package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

public class FileOut {

    public static void PDF(String csvFile) throws FileNotFoundException, IOException, DocumentException {
        Document doc = new Document();
        PdfPTable table = new PdfPTable(5);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineStrings = line.split(";");

                for (int i = 0; i < lineStrings.length; ++i) {
                    table.addCell(lineStrings[i]);
                }
            }
        }

        PdfWriter.getInstance(doc, new FileOutputStream("CSVtoPDF.pdf"));

        doc.open();
        doc.add(table);
        doc.close();

        System.out.printf("PDF file generated!\n");
    }

    public static void XLS(String csvFile) throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Assignment2");
        XSSFTable table = sheet.createTable(null);

        CTTable cttable = table.getCTTable();
        cttable.setDisplayName("Table1");
        cttable.setId(1);
        cttable.setName("Assignment2");
        cttable.setRef("A1:C11");
        cttable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            int rowNum = 0;
            int i = 0;

            while ((line = br.readLine()) != null) {
                String[] lineStrings = line.split(";");

                XSSFRow row = sheet.createRow(rowNum);
                for (i = 0; i < lineStrings.length; ++i) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellValue(lineStrings[i]);
                }

                ++rowNum;
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("CSVtoXLS.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        System.out.printf("XLS file generated!\n");
    }
}
