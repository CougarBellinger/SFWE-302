package com.assignment8.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RESTCountriesClient {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the country name:");
        String country = reader.readLine();
        reader.close();

        try {
            String encodedCountry = URLEncoder.encode(country, StandardCharsets.UTF_8);
            String apiUrl = "http://localhost:8000/country?country=" + encodedCountry;
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println("Server Response:");
                System.out.println(content.toString());
            } else {
                System.out.println("Error: " + status);
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
