package interface_adapter.loggedin.notification.CreateNotification;

import use_case.createNotification.CreateNotificationInputData;
import use_case.createNotification.CreateNotificationInputBoundary;


public class CreateNotificationController {

    final CreateNotificationInputBoundary myCreateNotificationInteractor;

    public CreateNotificationController(CreateNotificationInputBoundary myCreateNotificationInteractor) {
        this.myCreateNotificationInteractor = myCreateNotificationInteractor;
    }

    public void execute(String cityName, Boolean wantCurrentTemperature, Boolean wantDailyMaxMin,
                        Boolean wantPrecipitationChance, String notificationTime, String username) {
        CreateNotificationInputData createNotificationInputData = new CreateNotificationInputData(cityName,
                wantCurrentTemperature, wantDailyMaxMin, wantPrecipitationChance, notificationTime, username);

        myCreateNotificationInteractor.execute(createNotificationInputData);
    }

}
