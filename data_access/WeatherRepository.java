package data_access;

import entity.Location;
import entity.LocationWeatherData;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;

import java.io.IOException;

public class WeatherRepository implements LocationLookupDataAccessInterface, WeatherLookupDataAccessInterface {
    private final String apiKey;

    public WeatherRepository(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Location getLocation(String locationInput) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://api.weatherapi.com/v1/search.json?key=" + apiKey + "&q=" + locationInput)
                    .method("GET", body)
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200) {
                return null;
            }

            JSONArray jsonArray = new JSONArray(response.body().string());

            if(jsonArray.isEmpty()) {
                return null;
            }

            JSONObject locationData = jsonArray.getJSONObject(0);

            String locationName = locationData.getString("name");
            String locationRegion = locationData.getString("region");
            String locationCountry = locationData.getString("country");
            float locationLat = locationData.getFloat("lat");
            float locationLon = locationData.getFloat("lon");

            return new Location(locationName, locationRegion, locationCountry, locationLat, locationLon);
        } catch(IOException ex) {
            return null; //TODO: check/add error handling for API
        }
    }

    @Override
    public LocationWeatherData getWeather(Location location) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + locationInput + "&days=7")
                    .method("GET", body)
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject currentData = jsonObject.getJSONObject("current");
            JSONObject todayForecastData = jsonObject.getJSONObject("forecast")
                    .getJSONArray("forecastday")
                    .getJSONObject(0);

            float currTempC = currentData.getFloat("temp_c");
            String currentCondition = currentData.getJSONObject("condition").getString("text");
            int dailyChanceOfPrecipitation = Math.max(todayForecastData.getInt("daily_chance_of_rain"),
                    todayForecastData.getInt("daily_chance_of_snow"));
            float maxDailyTemp = todayForecastData.getFloat("maxtemp_c");
            float minDailyTemp = todayForecastData.getFloat("mintemp_c");

            return new LocationWeatherData(currTempC, currentCondition, dailyChanceOfPrecipitation, maxDailyTemp, minDailyTemp);
        } catch(IOException ex) {
            return null; //TODO: check/add error handling for API
        }
    }
}
