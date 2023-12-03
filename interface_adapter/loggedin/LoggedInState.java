package interface_adapter.loggedin;

public class LoggedInState {
    // For keeping track of the user that is currently using the application
    private String username = "";

    // For keeping track of the city the user is currently viewing/trying to view data for
    private String currentCityName = "";

    // Duplication initializer call
    public LoggedInState(LoggedInState duplicate) {
        username = duplicate.username;
        currentCityName = duplicate.currentCityName;
    }

    // empty initializer call
    public LoggedInState() {}

    // Get methods for private Instance Attributes
    public String getUsername() {
        return username;
    }
    public String getCurrentCityName() {return currentCityName; }

    // Setter methods for private Instance Attributes
    public void setUsername(String username) {
        this.username = username;
    }
    public void setCurrentCityName(String cityName) { this.currentCityName = cityName; }
}
