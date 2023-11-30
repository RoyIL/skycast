package interface_adapter.loggedin;

public class LoggedInState {
    private String username = "";

    private String currentCityName = "";

    public LoggedInState(LoggedInState duplicate) {
        username = duplicate.username;
    }
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public String getCurrentCityName() {return currentCityName; }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setCurrentCityName(String cityName) { this.currentCityName = cityName; }
}
