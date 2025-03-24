package part3;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RESTCountriesServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/country", new CountryHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class CountryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String country = null;
            if (query != null && query.startsWith("country=")) {
                country = query.substring("country=".length());
            }
            if (country == null || country.isEmpty()) {
                sendResponse(exchange, "Please specify a country");
            } else {
                try {
                    String encodedCountry = URLEncoder.encode(country, StandardCharsets.UTF_8);
                    String apiUrl = "http://universities.hipolabs.com/search?country=" + encodedCountry;
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(apiUrl))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    JSONArray universities = new JSONArray(response.body());
                    JSONObject responseData = new JSONObject();
                    responseData.put("totalUniversities", universities.length());
                    JSONArray universityNames = new JSONArray();
                    for (int i = 0; i < universities.length(); i++) {
                        JSONObject university = universities.getJSONObject(i);
                        universityNames.put(university.getString("name"));
                    }
                    responseData.put("universityNames", universityNames);
                    sendResponse(exchange, responseData.toString());
                } catch (Exception e) {
                    sendResponse(exchange, "Error processing request");
                    e.printStackTrace();
                }
            }
        }

        private void sendResponse(HttpExchange exchange, String response) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
