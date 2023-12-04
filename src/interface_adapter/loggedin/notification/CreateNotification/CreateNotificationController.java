package interface_adapter.loggedin.notification.CreateNotification;

import use_case.createNotification.CreateNotificationInputData;
import use_case.createNotification.CreateNotificationInputBoundary;


public class CreateNotificationController {

    final CreateNotificationInputBoundary myCreateNotificationInteractor;

    public CreateNotificationController(CreateNotificationInputBoundary myCreateNotificationInteractor) {
        this.myCreateNotificationInteractor = myCreateNotificationInteractor;
    }

    public void execute() {
        CreateNotificationInputData createNotificationInputData = new CreateNotificationInputData();

        myCreateNotificationInteractor.execute(createNotificationInputData);
    }

}
