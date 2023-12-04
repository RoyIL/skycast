package interface_adapter.loggedin.notification.CreateNotification;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.loggedin.notification.NotificationState;
import interface_adapter.loggedin.notification.NotificationViewModel;
import use_case.createNotification.CreateNotificationOutputBoundary;
import use_case.createNotification.CreateNotificationOutputData;

public class CreateNotificationPresenter implements CreateNotificationOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final NotificationViewModel notificationViewModel;

    public CreateNotificationPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                       NotificationViewModel notificationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.notificationViewModel = notificationViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String errorMessage) {
        NotificationState notificationState = notificationViewModel.getWindowState();
        notificationState.setErrorMessage(errorMessage);
        notificationViewModel.firePropertyChanged();
    }
}
