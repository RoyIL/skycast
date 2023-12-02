package use_case.weather_lookup;

import entity.Location;

public class WeatherLookupInputData {
    final private Location location;

    public WeatherLookupInputData(Location location) {
        this.location = location;
    }

    Location getLocation() {
        return location;
    }
}
