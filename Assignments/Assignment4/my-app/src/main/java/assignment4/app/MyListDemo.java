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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyListDemo extends URLLoader {
    protected List<Product> list = new ArrayList<>();
    protected Map<String, Double> map = null;
    protected Set<String> all = new HashSet<>();
    protected Set<String> duplicates = new HashSet<>();
    protected Set<String> oneOccurence = null;

    @Override
    protected void processLine(String tokens) {
    }

    @Override
    public void processLine(String[] tokens) {
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

    private void applySearch() {
        for (Product product : list) {
            String name = product.getName();
            if (!all.add(name)) { // Check documentation on what this means
                duplicates.add(name);
            }
        }

        map = list.stream()
                .sorted((o1, o2) -> o1.getTerritory().compareTo(o2.getTerritory()))
                .collect(Collectors.groupingBy(Product::getTerritory, Collectors.summingDouble(Product::getPrice)));

        // map = list.stream().collect(Collectors.groupingBy(Product::getTerritory));

        // map = list.stream()
        // .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
        // .collect(Collectors.groupingBy(Product::getTerritory));

        // for (Entry<String, Integer> entry : map.entrySet()) {
        // System.out.println(entry.getKey() + " " + entry.getValue());
        // }
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

            oneOccurence = new TreeSet<>(all);
            oneOccurence.removeAll(duplicates);
            for (String result : oneOccurence) {
                Row row = sheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result);
            }

            for (Product product : list) {
                Row row = sheet.createRow(rownum++);
                createList(product, row);
            }

            // LinkedHashMap<String, Integer> sortedMap = sortByValue(map);
            // for (Entry<String, Integer> result : sortedMap.entrySet()) {
            // Row row = sheet.createRow(rownum++);
            // Cell cell = row.createCell(0);
            // cell.setCellValue(result.getKey());

            // cell = row.createCell(1);
            // cell.setCellValue(result.getValue());
            // }

            for (Entry<String, Double> result : map.entrySet()) {
                Row row = sheet.createRow(rownum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(result.getKey());
                cell = row.createCell(1);
                cell.setCellValue(result.getValue());
            }

            FileOutputStream out = new FileOutputStream(new File("NewFile3.xlsx")); // file name with path
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public static LinkedHashMap<String, Integer> sortByValue(Map<String, Integer>
    // map) {
    // List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
    // list.sort(Entry.comparingByValue());
    // list.sort(new Comparator<Map.Entry<String, Integer>>() {
    // @Override
    // public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
    // if (o2.getValue().equals(o1.getValue())) {
    // return o1.getKey().compareTo(o2.getKey());
    // } else {
    // return o2.getValue().compareTo(o1.getValue());
    // }
    // }
    // });
    // LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
    // for (Entry<String, Integer> entry : list) {
    // result.put(entry.getKey(), entry.getValue());
    // }
    // return result;
    // }

    public static <K extends Comparable<? super K>, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(
            Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        // list.sort(Entry.comparingByValue());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                if (o2.getValue().equals(o1.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        MyListDemo myListDemo = new MyListDemo();

        myListDemo.loadData();
        myListDemo.applySearch();
        myListDemo.createXLS();

        System.out.println("Size all: " + myListDemo.list.size());
        System.out.println("Size unique: " + myListDemo.all.size());
        System.out.println("Size duplicates: " + myListDemo.duplicates.size());
        System.out.println("Size one occurence: " + myListDemo.oneOccurence.size());

    }
}
