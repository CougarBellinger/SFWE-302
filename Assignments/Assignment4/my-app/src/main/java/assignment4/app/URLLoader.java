package assignment4.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class URLLoader {
    protected abstract void processLine(String tokens);

    protected abstract void processLine(String[] tokens);

    public final void loadData() {
        String filePath = "Sample-Spreadsheet-1000-rows.csv";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), StandardCharsets.ISO_8859_1));

            String inputLine;
            String[] line;

            while ((inputLine = in.readLine()) != null) {
                line = inputLine.split(",");
                processLine(line);
            }

            if (in != null) {
                in.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
