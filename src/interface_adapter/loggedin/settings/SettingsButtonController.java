package interface_adapter.loggedin.settings;
 import use_case.settings_pressed.SettingsPressedInputBoundary;
 import use_case.settings_pressed.SettingsPressedInteractor;
 import use_case.settings_pressed.SettingsPressedOutputBoundary;

public class SettingsButtonController {
    private final SettingsPressedInputBoundary settingsPressedInteractor;

    public SettingsButtonController(SettingsPressedInputBoundary settingsPressedInteractor) {
        this.settingsPressedInteractor = settingsPressedInteractor;
    }
    public void execute(String username) {
        settingsPressedInteractor.execute(username);
    }

}
