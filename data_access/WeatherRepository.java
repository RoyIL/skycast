package data_access;

import entity.Location;
import okhttp3.*;
import org.json.JSONObject;
import use_case.location_lookup.LocationLookupDataAccessInterface;

import java.io.IOException;

public class WeatherRepository implements LocationLookupDataAccessInterface {
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
                    .url("http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + locationInput + "&days=0")
                    .method("GET", body)
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code() != 200) {
                return null;
            }

            JSONObject jsonObject = new JSONObject(response.body().string());
            JSONObject locationData = jsonObject.getJSONObject("location");

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
}
