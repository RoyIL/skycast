package use_case.weather_lookup;

import entity.Location;
import entity.LocationWeatherData;

public interface WeatherLookupDataAccessInterface {
    LocationWeatherData getWeather(Location location);
}
