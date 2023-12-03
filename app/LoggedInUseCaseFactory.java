package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.loggedin.notification.NotificationController;
import interface_adapter.loggedin.notification.NotificationPresenter;
import interface_adapter.loggedin.notification.NotificationViewModel;
import use_case.notifacation.NotificationInputBoundary;
import use_case.notifacation.NotificationInteractor;
import use_case.notifacation.NotificationOutputBoundary;
import view.LoggedInView;
import view.NotificationView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(ViewManagerModel viewManager, LoggedInViewModel loggedInViewModel, NotificationViewModel notificationViewModel) {
        NotificationController notificationController = createNotificationUseCase(viewManager, notificationViewModel);

        return new LoggedInView(loggedInViewModel, notificationController);
    }

    private static NotificationController createNotificationUseCase(ViewManagerModel viewManager, NotificationViewModel notificationViewModel) {
        NotificationOutputBoundary outputBoundary = new NotificationPresenter(viewManager, notificationViewModel);
        NotificationInputBoundary inputBoundary = new NotificationInteractor(outputBoundary);

        return new NotificationController(inputBoundary);
    }
}
