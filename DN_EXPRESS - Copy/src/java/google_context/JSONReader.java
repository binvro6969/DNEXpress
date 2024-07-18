/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package google_context;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;

public class JSONReader {   
    public static JSONObject fetchJSONFromURL(String url) throws IOException {
       CloseableHttpClient httpClient = HttpClients.createDefault();       
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return new JSONObject(jsonResponse);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    private static void processJsonData(JSONObject jsonData) {
        
        JSONArray destinationAddresses = jsonData.getJSONArray("destination_addresses");
        JSONArray originAddresses = jsonData.getJSONArray("origin_addresses");
        JSONArray rows = jsonData.getJSONArray("rows");

        System.out.println("Destinations: " + destinationAddresses);
        System.out.println("Origins: " + originAddresses);

        for (int i = 0; i < rows.length(); i++) {
            JSONObject row = rows.getJSONObject(i);
            JSONArray elements = row.getJSONArray("elements");
            for (int j = 0; j < elements.length(); j++) {
                JSONObject element = elements.getJSONObject(j);
                JSONObject distance = element.getJSONObject("distance");
                int distance_value = distance.getInt("value");               
                System.out.println("Distance: " + distance_value);
            }
        }
    }
}


