import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//call API for weather data - this backend logic will fetch the latest data
// data from the external API and return it, display the data to end user.
public class WeatherApp {
    // fetch weather data from given location
    public static JSONArray getWeatherData(String locationName) {
        //location coordinates using the geolocation api
        JSONArray locationData = getWeatherData(locationName);

        //extract latitude and longitude data
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        //Build API request URL with location coordinates
        String urlString = 

        return null;
    }

    //retrieves geo
    public static JSONArray getLocationData(String locationName) {
        //replace any whitespace in location name to + to adhere to API's request format
        locationName = locationName.replaceAll(" ", "+");

        //build API url with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try {
            //call api and get a response
            HttpURLConnection conn = fetchApiResponse(urlString);

            // check response status
            // 200 means successful connection

            if(conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to the API.");
                return null;
            }else{
                //store the API results
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                // Read and store the resulting json data into StringBuilder
                while(scanner.hasNextLine()) {
                    resultJson.append(scanner.nextLine());
                }

                // Scanner Cloe
                scanner.close();

                //Closing URL connection
                conn.disconnect();

                //parsing JSON String in JSON Obj
                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                //list of location data from the location name
                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        //could not find location
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set request method to get
            conn.setRequestMethod("GET");

            //connect to our API
            conn.connect();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //connection not established
        return null;
    }
}









