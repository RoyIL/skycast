package interface_adapter.loggedin.settings;

import use_case.setsettings.SetSettingsInputBoundary;
import use_case.setsettings.SetSettingsInputData;

public class SettingsController {

    final SetSettingsInputBoundary setSettingsInteractor;

    public SettingsController(SetSettingsInputBoundary setSettingsInteractor) {
        this.setSettingsInteractor = setSettingsInteractor;
    }

    public void execute(String newPassword, String newPhoneNumber) {
        SetSettingsInputData setSettingsInputData = new SetSettingsInputData(newPassword, newPhoneNumber);

        setSettingsInteractor.execute(setSettingsInputData);
    }
}
