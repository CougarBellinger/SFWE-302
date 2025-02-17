package assignment4.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyListDemo extends URLLoader {
    protected List<Product> list = new ArrayList<>();

    @Override
    protected void processLine(String tokens) {
    }

    @Override
    protected void processLine(String[] tokens) {
        if (tokens[7].equalsIgnoreCase("British Columbia")) {
            Product product = new Product();
            product.setId(Long.parseLong(tokens[0]));
            product.setName(tokens[1]);
            product.setAgentName(tokens[2]);
            product.setAgentId(Long.parseLong(tokens[3]));
            product.setPrice(Double.parseDouble(tokens[5]));
            product.setTerritory(tokens[7]);
            product.setCategory(tokens[8]);

            list.add(product);
        }
    }

    private void createList(Product product, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(product.getId());

        cell = row.createCell(1);
        cell.setCellValue(product.getName());

        cell = row.createCell(2);
        cell.setCellValue(product.getAgentName());

        cell = row.createCell(3);
        cell.setCellValue(product.getAgentId());

        cell = row.createCell(4);
        cell.setCellValue(product.getTerritory());

        cell = row.createCell(5);
        cell.setCellValue(product.getCategory());
    }

    protected void createXLS() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet
            int rownum = 0;
            for (Product product : list) {
                Row row = sheet.createRow(rownum++);
                createList(product, row);
            }
            FileOutputStream out = new FileOutputStream(new File("NewFile3.xlsx")); // file name with path
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyListDemo myListDemo = new MyListDemo();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());

        myListDemo.createXLS();
    }
}
