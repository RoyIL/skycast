package use_case.createNotification;

import javax.swing.text.StyledEditorKit;

public class CreateNotificationInputData {
    private String cityName;
    private Boolean wantDailyMaxMin;
    private Boolean wantPrecipitationChance;
    private Boolean wantCurrentTemperature;

    public CreateNotificationInputData(String cityName, Boolean wantCurrentTemperature, Boolean wantDailyMaxMin,
                                       Boolean wantPrecipitationChance) {
        this.cityName = cityName;
        this.wantCurrentTemperature = wantCurrentTemperature;
        this.wantDailyMaxMin = wantDailyMaxMin;
        this.wantPrecipitationChance = wantPrecipitationChance;
    }

    public String getCityName() {return cityName;}
    public Boolean getWantCurrentTemperature() {return wantCurrentTemperature;}
    public Boolean getWantDailyMaxMin() {return wantDailyMaxMin;}
    public Boolean getWantPrecipitationChance() {return wantPrecipitationChance;}
}
