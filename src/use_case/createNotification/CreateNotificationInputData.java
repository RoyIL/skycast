package use_case.createNotification;

import interface_adapter.loggedin.notification.NotificationState;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;

public class CreateNotificationInputData {
    private String cityName;
    private Boolean wantDailyMaxMin;
    private Boolean wantPrecipitationChance;
    private Boolean wantCurrentTemperature;
    private String notificationTime;
    private String username;

    public CreateNotificationInputData(String cityName, Boolean wantCurrentTemperature, Boolean wantDailyMaxMin,
                                       Boolean wantPrecipitationChance, String notificationTime, String username) {
        this.cityName = cityName;
        this.wantCurrentTemperature = wantCurrentTemperature;
        this.wantDailyMaxMin = wantDailyMaxMin;
        this.wantPrecipitationChance = wantPrecipitationChance;
        this.notificationTime = notificationTime;
        this.username = username;
    }

    public String getCityName() {return cityName;}
    public Boolean getWantCurrentTemperature() {return wantCurrentTemperature;}
    public Boolean getWantDailyMaxMin() {return wantDailyMaxMin;}
    public Boolean getWantPrecipitationChance() {return wantPrecipitationChance;}
    public String getNotificationTime() {return notificationTime;}
    public String getUsername() {return username;}
}
