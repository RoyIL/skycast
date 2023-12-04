package interface_adapter.loggedin;

import entity.Location;
import entity.LocationWeatherData;

public class LoggedInState {
    // For keeping track of the user that is currently using the application
    private String username = "";

    // For keeping track of the city the user is currently viewing/trying to view data for
    private String currentCityName = "";

    // For keeping track of the location the user has retrieved (searched)
    private Location currentRetrievedLocation = null;

    private LocationWeatherData locationWeatherData = null;

    private String locationWeatherDataLatLon = "";

    // Duplication initializer call
    public LoggedInState(LoggedInState duplicate) {
        username = duplicate.username;
        currentCityName = duplicate.currentCityName;
        currentRetrievedLocation = duplicate.currentRetrievedLocation;
        locationWeatherData = duplicate.locationWeatherData;
        locationWeatherDataLatLon = duplicate.locationWeatherDataLatLon;
    }

    // empty initializer call
    public LoggedInState() {
    }

    // Get methods for private Instance Attributes
    public String getUsername() {
        return username;
    }

    public String getCurrentCityName() {
        return currentCityName;
    }

    public Location getCurrentRetrievedLocation() {
        return currentRetrievedLocation;
    }

    public LocationWeatherData getLocationWeatherData() {
        return locationWeatherData;
    }

    public String getLocationWeatherDataLatLon() {
        return locationWeatherDataLatLon;
    }

    // Setter methods for private Instance Attributes
    public void setUsername(String username) {
        this.username = username;
    }

    public void setCurrentCityName(String cityName) {
        this.currentCityName = cityName;
    }

    public void setCurrentRetrievedLocation(Location currentRetrievedLocation) {
        this.currentRetrievedLocation = currentRetrievedLocation;
    }

    public void setLocationWeatherData(LocationWeatherData locationWeatherData) {
        this.locationWeatherData = locationWeatherData;
    }

    public void setLocationWeatherDataLatLon(String locationWeatherDataLatLon) {
        this.locationWeatherDataLatLon = locationWeatherDataLatLon;
    }
}
