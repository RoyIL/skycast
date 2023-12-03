package interface_adapter.loggedin.notification;

import use_case.notifacation.NotificationInputBoundary;
import use_case.notifacation.NotificationInputData;

public class NotificationController {
    // Variable declaration creating an instance to call the execute function
    final NotificationInputBoundary createNotificationInteractor;

    // Initializer
    public NotificationController(NotificationInputBoundary createNotificationInteractor) {
        this.createNotificationInteractor = createNotificationInteractor;
    }

    // Execute call through the input boundary interface
    public void execute() {
        // Create an instance of NotificationInputData
        NotificationInputData notificationInputData = new NotificationInputData();

        // Use input boundary instance to call execute function
        createNotificationInteractor.execute(notificationInputData);
    }
}
