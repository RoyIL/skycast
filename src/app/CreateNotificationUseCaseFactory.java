package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.loggedin.notification.CreateNotification.CreateNotificationController;
import interface_adapter.loggedin.notification.CreateNotification.CreateNotificationPresenter;
import interface_adapter.loggedin.notification.NotificationController;
import interface_adapter.loggedin.notification.NotificationPresenter;
import interface_adapter.loggedin.notification.NotificationViewModel;
import use_case.createNotification.*;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.notifacation.NotificationInputBoundary;
import use_case.notifacation.NotificationInteractor;
import use_case.notifacation.NotificationOutputBoundary;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;
import view.NotificationView;

public class CreateNotificationUseCaseFactory {
    private CreateNotificationUseCaseFactory() {}

    public static NotificationView create(CreateNotificationDataAccessInterface dataAccessInterface,
                                          LocationLookupDataAccessInterface locationLookupDataAccessInterface,
                                          WeatherLookupDataAccessInterface weatherLookupDataAccessInterface,
                                          CreateNotificationServiceInterface notificationServiceInterface, ViewManagerModel viewManager,
                                          LoggedInViewModel loggedInViewModel, NotificationViewModel notificationViewModel) {
        CreateNotificationController createNotificationController = createNotificationUseCase(dataAccessInterface,
                locationLookupDataAccessInterface, weatherLookupDataAccessInterface, notificationServiceInterface,
                viewManager, loggedInViewModel, notificationViewModel);

        return new NotificationView(notificationViewModel, createNotificationController);
    }

    private static CreateNotificationController createNotificationUseCase(
            CreateNotificationDataAccessInterface dataAccessInterface,
            LocationLookupDataAccessInterface locationLookupDataAccessInterface,
            WeatherLookupDataAccessInterface weatherLookupDataAccessInterface,
            CreateNotificationServiceInterface notificationServiceInterface, ViewManagerModel viewManager,
            LoggedInViewModel loggedInViewModel, NotificationViewModel notificationViewModel) {

        CreateNotificationOutputBoundary outputBoundary = new CreateNotificationPresenter(viewManager,
                loggedInViewModel, notificationViewModel);

        CreateNotificationInputBoundary inputBoundary = new CreateNotificationInteractor(dataAccessInterface,
                locationLookupDataAccessInterface, weatherLookupDataAccessInterface, outputBoundary,
                notificationServiceInterface);

        return new CreateNotificationController(inputBoundary);
    }
}
