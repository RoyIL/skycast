package interface_adapter.loggedin.notification;

import use_case.notifacation.NotificationInputBoundary;
import use_case.notifacation.NotificationInputData;

public class NotificationController {
    final NotificationInputBoundary createNotificationInteractor;

    public NotificationController(NotificationInputBoundary createNotificationInteractor) {
        this.createNotificationInteractor = createNotificationInteractor;
    }

    public void execute() {
        NotificationInputData notificationInputData = new NotificationInputData();

        createNotificationInteractor.execute(notificationInputData);
    }
}
