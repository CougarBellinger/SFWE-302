package assignment4.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class URLLoader {
    protected abstract void processLine(String tokens);

    protected abstract void processLine(String[] tokens);

    public final void loadData() {
        URL url = null;
        BufferedReader in = null;
        try {
            url = new URL("https://sample-videos.com/csv/Sample-Spreadsheet-1000-rows.csv");
            // this works on Cerny's try UTF-8 if messy chars
            in = new BufferedReader(new InputStreamReader(url.openStream(),
                    StandardCharsets.ISO_8859_1));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
