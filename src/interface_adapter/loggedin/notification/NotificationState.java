package interface_adapter.loggedin.notification;

import java.util.ArrayList;

public class NotificationState {
    // Variable declaration

    // String type declarations
    private String username = "";
    private String cityName = "";
    private String notificationTime = "";
    private String errorMessage = null;

    // Boolean Object declaration
    private Boolean wantPrecipitationChance = Boolean.FALSE;
    private Boolean wantDailyMaxMin = Boolean.FALSE;
    private Boolean wantCurrentTemperature = Boolean.FALSE;

    // TODO ADD PHONE NUMBER INPUT TO VIEW
    private String phoneNumber = "2268689717";

    // Duplicate method for the view state
    public NotificationState(NotificationState duplicate) {
        username = duplicate.username;
        cityName = duplicate.cityName;
        wantPrecipitationChance = duplicate.wantPrecipitationChance;
        wantDailyMaxMin = duplicate.wantDailyMaxMin;
        wantCurrentTemperature = duplicate.wantCurrentTemperature;
        errorMessage = duplicate.errorMessage;
        notificationTime = duplicate.notificationTime;
    }

    // Void initializer
    public NotificationState() {
    }

    // getter functions

    // Getter string functions
    public String getCityName() {return cityName;}
    public String getErrorMessage() {return errorMessage;}
    public String getNotificationTime() {return notificationTime;}

    public String getUsername() {return username;}
    // Getter boolean functions
    public Boolean getWantPrecipitationChance() {return wantPrecipitationChance;};
    public Boolean getWantDailyMaxMin() {return wantDailyMaxMin;}
    public Boolean getWantCurrentTemperature() {return wantCurrentTemperature;}

    // Setter functions

    // Setter string functions
    public void setCityName(String newCityName) {cityName = newCityName;}
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}
    public void setNotificationTime(String notificationTime) {this.notificationTime = notificationTime;}
    public void setUsername(String username) {this.username = username;}

    // Setter boolean Functions
    public void setWantPrecipitationChance(Boolean newBoolValue) {wantPrecipitationChance = newBoolValue;}
    public void setWantDailyMaxMin(Boolean newBoolValue) {wantDailyMaxMin = newBoolValue;}
    public void setWantCurrentTemperature(Boolean newBoolValue) {wantCurrentTemperature = newBoolValue;}
}
