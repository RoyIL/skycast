package interface_adapter.loggedin.settings;


import use_case.settings_pressed.SettingsPressedOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.settings.SettingsViewModel;

public class SettingsButtonPresenter implements SettingsPressedOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private SettingsViewModel settingsViewModel;

    public SettingsButtonPresenter(ViewManagerModel viewManagerModel, SettingsViewModel settingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingsViewModel = settingsViewModel;
    }

    public void prepareSuccessView(String username) {
        SettingsState settingsState = settingsViewModel.getWindowState();
        settingsState.setUsername(username);
        settingsViewModel.setWindowState(settingsState);
        viewManagerModel.setActiveView(settingsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
