package com.assignment8.part2;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RESTCountries {
    public static void main(String[] argv) throws Exception {
        String[] countryNames = { "Czechia", "Germany", "United States of America" };

        for (String countryName : countryNames) {
            String encodedCountryName = URLEncoder.encode(countryName, "UTF-8");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://restcountries.com/v3.1/name/" + encodedCountryName + "?fullText=true"))
                    .header("content-type", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();

            System.out.println("Response for " + countryName + ":");
            System.out.println(result);

            if (result.startsWith("{\"message\":\"Page Not Found\"")) {
                System.out.println("Error: Country data not found for " + countryName);
                continue;
            }
            JSONArray ja = new JSONArray(result);
            JSONObject jo = ja.getJSONObject(0);

            System.out.println("Country Details for " + countryName + ":");

            if (jo.has("name")) {
                JSONObject nameObject = jo.getJSONObject("name");
                if (nameObject.has("common")) {
                    System.out.println("Name: " + nameObject.getString("common"));
                } else {
                    System.out.println("Name not available.");
                }
            } else {
                System.out.println("Name not available.");
            }

            if (jo.has("tld")) {
                JSONArray tldArray = jo.getJSONArray("tld");
                if (tldArray.length() > 0) {
                    System.out.println("Top-Level Domain: " + tldArray.getString(0));
                } else {
                    System.out.println("Top-Level Domain not available.");
                }
            } else {
                System.out.println("Top-Level Domain not available.");
            }

            if (jo.has("capital")) {
                JSONArray capitalArray = jo.getJSONArray("capital");
                if (capitalArray.length() > 0) {
                    System.out.println("Capital: " + capitalArray.getString(0));
                } else {
                    System.out.println("Capital not available.");
                }
            } else {
                System.out.println("Capital not available.");
            }

            if (jo.has("languages")) {
                System.out.println("Languages:");
                JSONObject languages = jo.getJSONObject("languages");
                for (String key : languages.keySet()) {
                    System.out.println("- " + key + ": " + languages.getString(key));
                }
            } else {
                System.out.println("Languages information not available.");
            }

            if (jo.has("name") && jo.getJSONObject("name").has("nativeName")) {
                JSONObject nativeNameObject = jo.getJSONObject("name").getJSONObject("nativeName");
                if (nativeNameObject.has("common")) {
                    System.out.println("Native Name: " + nativeNameObject.getString("common"));
                } else {
                    System.out.println("Native Name not available.");
                }
            } else {
                System.out.println("Native Name not available.");
            }

            System.out.println();
        }
    }
}
