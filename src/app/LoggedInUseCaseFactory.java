package app;

import data_access.WeatherRepository;
import interface_adapter.ViewManagerModel;
import interface_adapter.location_lookup.LocationLookupController;
import interface_adapter.location_lookup.LocationLookupPresenter;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.loggedin.notification.NotificationController;
import interface_adapter.loggedin.notification.NotificationPresenter;
import interface_adapter.loggedin.notification.NotificationViewModel;
import interface_adapter.loggedin.settings.SettingsButtonController;
import interface_adapter.loggedin.settings.SettingsButtonPresenter;
import interface_adapter.loggedin.settings.SettingsViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login_signup_switch.LoginSignupSwitchController;
import interface_adapter.login_signup_switch.LoginSignupSwitchPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.weather_lookup.WeatherLookupController;
import interface_adapter.weather_lookup.WeatherLookupPresenter;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.location_lookup.LocationLookupInputBoundary;
import use_case.location_lookup.LocationLookupInteractor;
import use_case.location_lookup.LocationLookupOutputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchInputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchInteractor;
import use_case.login_signup_switch.LoginSignupSwitchOutputBoundary;
import use_case.notifacation.NotificationInputBoundary;
import use_case.notifacation.NotificationInteractor;
import use_case.notifacation.NotificationOutputBoundary;
import use_case.settings_pressed.SettingsPressedInputBoundary;
import use_case.settings_pressed.SettingsPressedInteractor;
import use_case.settings_pressed.SettingsPressedOutputBoundary;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;
import use_case.weather_lookup.WeatherLookupInputBoundary;
import use_case.weather_lookup.WeatherLookupInteractor;
import use_case.weather_lookup.WeatherLookupOutputBoundary;
import view.LoggedInView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory() {
    }

    public static LoggedInView create(ViewManagerModel viewManager, LoggedInViewModel loggedInViewModel,
                                      NotificationViewModel notificationViewModel,
                                      WeatherRepository dataAccessInterface, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
                                      SettingsViewModel settingsViewModel) {
        NotificationController notificationController = createNotificationUseCase(viewManager, notificationViewModel);
        LocationLookupController locationLookupController = createLocationLookupUseCase(loggedInViewModel, viewManager, dataAccessInterface);
        WeatherLookupController weatherLookupController = createWeatherLookupUseCase(loggedInViewModel, viewManager, dataAccessInterface);
        LoginSignupSwitchController loginSignupSwitchController = createLoginSignupSwitchUseCase(viewManager, loginViewModel, signupViewModel);
        SettingsButtonController settingsButtonController = createSettingsButtonController(viewManager, settingsViewModel);

        return new LoggedInView(loggedInViewModel, notificationController, locationLookupController, weatherLookupController, loginSignupSwitchController, settingsButtonController);
    }

    private static NotificationController createNotificationUseCase(ViewManagerModel viewManager, NotificationViewModel notificationViewModel) {
        NotificationOutputBoundary outputBoundary = new NotificationPresenter(viewManager, notificationViewModel);
        NotificationInputBoundary inputBoundary = new NotificationInteractor(outputBoundary);

        return new NotificationController(inputBoundary);
    }

    private static LocationLookupController createLocationLookupUseCase(
            LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
            LocationLookupDataAccessInterface dataAccessInterface) {
        LocationLookupOutputBoundary outputBoundary = new LocationLookupPresenter(loggedInViewModel, viewManagerModel);
        LocationLookupInputBoundary inputBoundary = new LocationLookupInteractor(dataAccessInterface, outputBoundary);

        return new LocationLookupController(inputBoundary);
    }

    private static WeatherLookupController createWeatherLookupUseCase(
            LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
            WeatherLookupDataAccessInterface dataAccessInterface) {
        WeatherLookupOutputBoundary outputBoundary = new WeatherLookupPresenter(loggedInViewModel, viewManagerModel);
        WeatherLookupInputBoundary inputBoundary = new WeatherLookupInteractor(dataAccessInterface, outputBoundary);

        return new WeatherLookupController(inputBoundary);
    }

    private static LoginSignupSwitchController createLoginSignupSwitchUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        LoginSignupSwitchOutputBoundary outputBoundary = new LoginSignupSwitchPresenter(loginViewModel, signupViewModel, viewManagerModel);
        LoginSignupSwitchInputBoundary inputBoundary = new LoginSignupSwitchInteractor(outputBoundary);

        return new LoginSignupSwitchController(inputBoundary);
    }

    private static SettingsButtonController createSettingsButtonController(ViewManagerModel viewManager, SettingsViewModel settingsViewModel) {
        SettingsPressedOutputBoundary settingsPressedOutputBoundary = new SettingsButtonPresenter(viewManager, settingsViewModel);
        SettingsPressedInputBoundary settingsPressedInteractor = new SettingsPressedInteractor(settingsPressedOutputBoundary);

        return new SettingsButtonController(settingsPressedInteractor);
    }
}
