package use_case.createNotification;

import use_case.notifacation.NotificationInputData;
import use_case.notifacation.NotificationOutputBoundary;
import use_case.notifacation.NotificationOutputData;

public class CreateNotificationInteractor {
    final CreateNotificationOutputBoundary presenter;

    public CreateNotificationInteractor(CreateNotificationOutputBoundary presenter) {
        this.presenter = presenter;
    }

    public void execute(NotificationInputData notificationInputData) {
        presenter.prepareSuccessView(new CreateNotificationOutputData());
    }
}
