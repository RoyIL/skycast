package use_case.setsettings;

import entity.CommonUser;
import interface_adapter.loggedin.settings.SettingsPresenter;
import use_case.setsettings.SetSettingsOutputData;
import use_case.setsettings.SetSettingsOutputBoundary;
import interface_adapter.loggedin.settings.SettingsState;
import use_case.settings_pressed.SettingsPressedOutputBoundary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class SetSettingsInteractor implements SetSettingsInputBoundary{

    private String newPassword;
    private String newPhoneNumber;
    private String oldPassword;
    private String oldPhoneNumber;
    private SetSettingsOutputBoundary settingsPresenter;
    private Pattern pattern = Pattern.compile("^\\d{10}$");
    private Matcher matcher;
    private SetSettingsOutputData outputData;
    private String username;

    private SetSettingsDataAccessInterface accessObject;

    public SetSettingsInteractor(SetSettingsDataAccessInterface accessObject, SetSettingsOutputBoundary presenter) {
        this.accessObject = accessObject;
        this.settingsPresenter = presenter;
    }

    public void execute(SetSettingsInputData setSettingsInputData) {
        username = setSettingsInputData.getUsername();
        newPassword = setSettingsInputData.getNewPassword();
        newPhoneNumber = setSettingsInputData.getNewPhoneNumber();
        oldPassword = accessObject.get(username).getPassword();
        oldPhoneNumber = accessObject.get(username).getPhoneNumber();
        // Testing
        System.out.println(username + ", " + oldPassword + ", " + oldPhoneNumber);
        System.out.println(username + ", " + newPassword + ", " + newPhoneNumber);

        Matcher matcher = pattern.matcher(newPhoneNumber);
        if (!matcher.matches() && !newPhoneNumber.isBlank()) {
            settingsPresenter.prepareFailView("Phone Number must be in the form XXXXXXXXXX");
        }
        else if (newPassword.isBlank() && !newPhoneNumber.isBlank()) {
            CommonUser user = new CommonUser(username, oldPassword, LocalDateTime.now(), newPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(oldPassword, newPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        }
        else if (!newPassword.isBlank() && newPhoneNumber.isBlank()) {
            CommonUser user = new CommonUser(username, newPassword, LocalDateTime.now(), oldPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(newPassword, oldPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        }
        else if (!newPassword.isBlank()) {
            CommonUser user = new CommonUser(username, newPassword, LocalDateTime.now(), newPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(newPassword, newPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        }
        else {
            settingsPresenter.prepareSuccessView(new SetSettingsOutputData(oldPassword, oldPhoneNumber));

        }
    }

}

