package interface_adapter.loggedin.notification;

public class NotificationState {
    // Variable declaration

    // String type declarations
    private String cityName = "";
    private String errorMessage = null;

    // Boolean Object declaration
    private Boolean wantPrecipitationChance = Boolean.FALSE;
    private Boolean wantDailyMaxMin = Boolean.FALSE;
    private Boolean wantCurrentTemperature = Boolean.FALSE;

    // TODO ADD PHONE NUMBER INPUT TO VIEW
    private String phoneNumber = "2268689717";

    // Duplicate method for the view state
    public NotificationState(NotificationState duplicate) {
        cityName = duplicate.cityName;
        wantPrecipitationChance = duplicate.wantPrecipitationChance;
        wantDailyMaxMin = duplicate.wantDailyMaxMin;
        wantCurrentTemperature = duplicate.wantCurrentTemperature;
        errorMessage = duplicate.errorMessage;
    }

    // Void initializer
    public NotificationState() {
    }

    // getter functions

    // Getter string functions
    public String getCityName() {return cityName;}
    public String getErrorMessage() {return errorMessage;}
    // Getter boolean functions
    public Boolean getWantPrecipitationChance() {return wantPrecipitationChance;};
    public Boolean getWantDailyMaxMin() {return wantDailyMaxMin;}
    public Boolean getWantCurrentTemperature() {return wantCurrentTemperature;}

    // Setter functions

    // Setter string functions
    public void setCityName(String newCityName) {cityName = newCityName;}
    public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}

    // Setter boolean Functions
    public void setWantPrecipitationChance(Boolean newBoolValue) {wantPrecipitationChance = newBoolValue;}
    public void setWantDailyMaxMin(Boolean newBoolValue) {wantDailyMaxMin = newBoolValue;}
    public void setWantCurrentTemperature(Boolean newBoolValue) {wantCurrentTemperature = newBoolValue;}
}
