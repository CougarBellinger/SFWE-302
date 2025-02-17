package assignment4.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class URLLoader {
    protected abstract void processLine(String tokens);

    protected abstract void processLine(String[] tokens);

    public final void loadData() {
        File in = new File("Sample-Spreadsheet-1000-rows.csv");
        try {
            String inputLine;
            String[] line;
            Scanner reader = new Scanner(in);
            while (reader.hasNextLine()) {
                inputLine = reader.nextLine();
                line = inputLine.split(",");
                processLine(line);
            }

            if (in != null) {
                reader.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
