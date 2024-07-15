import org.json.simple.JSONObject;

//call API for weather data - this backend logic will fetch the latest data
// data from the external API and return it, display the data to end user.
public class WeatherApp {
    // fetch weather data from given location
    public static JSONObject getWeatherData(String locationName){
        JSONArray locationData = getWeatherData(locationName);

    }
    private static JSONArray getLocationData (String locationName )
        // replace any whitespace in location name to + to adhere to API's request format
    locationName = locationName.replaceAll()
}
