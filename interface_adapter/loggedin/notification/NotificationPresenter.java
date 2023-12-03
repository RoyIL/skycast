package interface_adapter.loggedin.notification;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.notification.NotificationViewModel;
import use_case.notifacation.NotificationOutputBoundary;
import use_case.notifacation.NotificationOutputData;

public class NotificationPresenter implements NotificationOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final NotificationViewModel notificationViewModel;

    public NotificationPresenter(ViewManagerModel viewManagerModel, NotificationViewModel notificationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.notificationViewModel = notificationViewModel;
    }

    public void prepareSuccessView(NotificationOutputData notification) {
        NotificationState newWindowState = notificationViewModel.getWindowState();
        notificationViewModel.setWindowState(newWindowState);

        // FROM TESTING System.out.println(notificationViewModel.getWindowState().getCityName());

        viewManagerModel.setActiveView(notificationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
