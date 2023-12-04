package use_case.notification;

public class NotificationInteractor implements NotificationInputBoundary {
    final NotificationOutputBoundary presenter;

    public NotificationInteractor(NotificationOutputBoundary presenter) {
        this.presenter = presenter;
    }

    public void execute(NotificationInputData notificationInputData) {
        presenter.prepareSuccessView(new NotificationOutputData(notificationInputData.getUsername()));
    }
}
