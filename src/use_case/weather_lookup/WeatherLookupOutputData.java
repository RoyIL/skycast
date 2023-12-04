package use_case.weather_lookup;

import entity.LocationWeatherData;
import entity.LocationWeatherForecastData;

public class WeatherLookupOutputData {
    private final LocationWeatherData locationWeatherData;

    public WeatherLookupOutputData(LocationWeatherData locationWeatherData) {
        this.locationWeatherData = locationWeatherData;
    }

    public LocationWeatherData getLocationWeatherData() {
        return locationWeatherData;
    }
}
