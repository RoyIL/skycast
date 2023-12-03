package interface_adapter.loggedin.notification;

public class NotificationState {
    private String cityName = "";
    private Boolean wantPrecipitationChance = Boolean.FALSE;
    private Boolean wantDailyMaxMin = Boolean.FALSE;
    private Boolean wantCurrentTemperature = Boolean.FALSE;

    private String phoneNumber = "2268689717";

    public NotificationState(NotificationState duplicate) {
        cityName = duplicate.cityName;
        wantPrecipitationChance = duplicate.wantPrecipitationChance;
        wantDailyMaxMin = duplicate.wantDailyMaxMin;
        wantCurrentTemperature = duplicate.wantCurrentTemperature;
    }

    public NotificationState() {
        // void initializer body to differentiate from copy function
    }

    // Temporary variable in case Daniel's settings are not complete

    public String getCityName() {return cityName;}
    public Boolean getWantPrecipitationChance() {return wantPrecipitationChance;};
    public Boolean getWantDailyMaxMin() {return wantDailyMaxMin;}
    public Boolean getWantCurrentTemperature() {return wantCurrentTemperature;}
    public void setCityName(String newCityName) {cityName = newCityName;}
    public void setWantPrecipitationChance(Boolean newBoolValue) {wantPrecipitationChance = newBoolValue;}
    public void setWantDailyMaxMin(Boolean newBoolValue) {wantDailyMaxMin = newBoolValue;}
    public void setWantCurrentTemperature(Boolean newBoolValue) {wantCurrentTemperature = newBoolValue;}
}
