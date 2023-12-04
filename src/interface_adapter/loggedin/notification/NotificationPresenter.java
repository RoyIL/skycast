package interface_adapter.loggedin.notification;

import interface_adapter.ViewManagerModel;
import use_case.notification.NotificationOutputBoundary;
import use_case.notification.NotificationOutputData;

public class NotificationPresenter implements NotificationOutputBoundary {

    // Variable declaration for storage of ViewManagerModel or and NotificationViewModel
    private final ViewManagerModel viewManagerModel;
    private final NotificationViewModel notificationViewModel;

    // Initializer
    public NotificationPresenter(ViewManagerModel viewManagerModel, NotificationViewModel notificationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notificationViewModel = notificationViewModel;
    }

    // Method for how to update the view after the use case runs successfuly
    @Override
    public void prepareSuccessView(NotificationOutputData notification) {
        NotificationState updaterState = notificationViewModel.getWindowState();
        updaterState.setUsername(notification.getUsername());


        // Sets the active view name to the view name of NotificationView
        viewManagerModel.setActiveView(notificationViewModel.getViewName());
        // Creates a property changed event to update the view
        viewManagerModel.firePropertyChanged();
    }
}
