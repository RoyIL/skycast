package app;

import interface_adapter.*;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.setsettings.SetSettingsInteractor;
import use_case.setsettings.SetSettingsOutputBoundary;
import use_case.setsettings.SetSettingsInputBoundary;
import use_case.setsettings.SetSettingsDataAccessInterface;
import use_case.setsettings.UsernameInputData;
import view.SettingsView;
import interface_adapter.loggedin.settings.SettingsViewModel;
import interface_adapter.loggedin.settings.SettingsPresenter;
import interface_adapter.loggedin.settings.SettingsController;

public class SettingsUseCaseFactory {

    private SettingsUseCaseFactory() {}

    public static SettingsView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, SettingsViewModel settingsViewModel, SetSettingsDataAccessInterface accessObject) {
        SettingsController settingsController = createSettingsUseCase(viewManagerModel, loggedInViewModel, settingsViewModel, accessObject);
        return new SettingsView(settingsViewModel, settingsController, viewManagerModel, loggedInViewModel);
    }

    private static SettingsController createSettingsUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                                            SettingsViewModel settingsViewModel, SetSettingsDataAccessInterface accessObject) {
        LoggedInState loggedInState = loggedInViewModel.getWindowState();
        UsernameInputData inputData = new UsernameInputData(loggedInState.getUsername());
        SetSettingsOutputBoundary outputBoundary = new SettingsPresenter(settingsViewModel, viewManagerModel);
        SetSettingsInputBoundary inputBoundary = new SetSettingsInteractor(accessObject, outputBoundary, inputData);

        return new SettingsController(inputBoundary);
    }
}
