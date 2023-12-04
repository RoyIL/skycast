package use_case.settings_pressed;

import interface_adapter.loggedin.settings.SettingsButtonPresenter;
import use_case.settings_pressed.SettingsPressedOutputBoundary;
public class SettingsPressedInteractor implements SettingsPressedInputBoundary {

    private SettingsPressedOutputBoundary settingsButtonPresenter;

    public SettingsPressedInteractor(SettingsPressedOutputBoundary settingsButtonPresenter) {
        this.settingsButtonPresenter = settingsButtonPresenter;
    }

    @Override
    public void execute(String username) {
        settingsButtonPresenter.prepareSuccessView(username);
    }
}