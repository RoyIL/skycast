package interface_adapter.loggedin.notification.CreateNotification;

import use_case.createNotification.CreateNotificationInputData;
import use_case.createNotification.CreateNotificationInputBoundary;


public class CreateNotificationController {

    final CreateNotificationInputBoundary myCreateNotificationInteractor;

    public CreateNotificationController(CreateNotificationInputBoundary myCreateNotificationInteractor) {
        this.myCreateNotificationInteractor = myCreateNotificationInteractor;
    }

    public void execute(String cityName, Boolean wantCurrentTemperature, Boolean wantDailyMaxMin,
                        Boolean wantPrecipitationChance) {
        CreateNotificationInputData createNotificationInputData = new CreateNotificationInputData(cityName,
                wantCurrentTemperature, wantDailyMaxMin, wantPrecipitationChance);

        myCreateNotificationInteractor.execute(createNotificationInputData);
    }

}
