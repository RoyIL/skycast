package data_access;

import entity.Location;
import entity.LocationWeatherData;
import entity.LocationWeatherForecastData;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.IOException;

public class WeatherRepository implements LocationLookupDataAccessInterface, WeatherLookupDataAccessInterface {
    private final String apiKey;
    private final int FORECAST_DAYS = 7;

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
                    .url("http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + location.getLat() + ","
                            + location.getLon() + "&days=" + Math.max(14, FORECAST_DAYS))
                    .method("GET", body)
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject currentData = jsonObject.getJSONObject("current");
            JSONArray forecastDataArray = jsonObject.getJSONObject("forecast").getJSONArray("forecastday");
            JSONObject todayForecastData = forecastDataArray.getJSONObject(0);

            float currTempC = currentData.getFloat("temp_c");
            String currentCondition = currentData.getJSONObject("condition").getString("text");
            int dailyChanceOfPrecipitation = Math.max(todayForecastData.getInt("daily_chance_of_rain"),
                    todayForecastData.getInt("daily_chance_of_snow"));
            float maxDailyTemp = todayForecastData.getFloat("maxtemp_c");
            float minDailyTemp = todayForecastData.getFloat("mintemp_c");

            LocationWeatherForecastData[] weatherForecast = new LocationWeatherForecastData[Math.max(14, FORECAST_DAYS) - 1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for(int i = 1; i < forecastDataArray.length(); i++) {
                JSONObject rawForecastData = forecastDataArray.getJSONObject(i);

                LocalDate forecastDate = LocalDate.parse(rawForecastData.getString("date"), formatter);
                int chanceOfPrecipitation = Math.max(rawForecastData.getInt("daily_chance_of_rain"),
                        rawForecastData.getInt("daily_chance_of_snow"));
                float maxTemp = rawForecastData.getFloat("maxtemp_c");
                float minTemp = rawForecastData.getFloat("mintemp_c");

                weatherForecast[i - 1] = new LocationWeatherForecastData(forecastDate, maxTemp, minTemp, chanceOfPrecipitation);
            }

            return new LocationWeatherData(currTempC, currentCondition, dailyChanceOfPrecipitation, maxDailyTemp, minDailyTemp, weatherForecast);
        } catch(IOException ex) {
            return null; //TODO: check/add error handling for API
        }
    }
}
