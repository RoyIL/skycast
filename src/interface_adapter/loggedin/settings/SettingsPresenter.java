package interface_adapter.loggedin.settings;

import interface_adapter.ViewManagerModel;
import view.LoggedInView;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import use_case.setsettings.SetSettingsOutputBoundary;
import use_case.setsettings.SetSettingsOutputData;

public class SettingsPresenter implements SetSettingsOutputBoundary{

    private ViewManagerModel viewManagerModel;
    private SettingsViewModel settingsViewModel;

    public SettingsPresenter (SettingsViewModel settingsViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingsViewModel = settingsViewModel;
    }
    @Override
    public void prepareSuccessView(SetSettingsOutputData output) {
        SettingsState settingsState = settingsViewModel.getWindowState();
        settingsState.setPassword(output.getNewPassword());
        settingsState.setPhoneNumber(output.getNewPhoneNumber());
        this.settingsViewModel.setWindowState(settingsState);
        this.settingsViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(settingsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SettingsState settingsState = settingsViewModel.getWindowState();
        settingsState.setPhoneNumberError(error);
        settingsViewModel.firePropertyChanged();
    }
}
