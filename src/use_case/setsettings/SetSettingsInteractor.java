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
    private Pattern pattern = Pattern.compile("\\d{10}");
    private Matcher matcher;
    private SetSettingsOutputData outputData;
    private String username;

    private SetSettingsDataAccessInterface accessObject;

    public SetSettingsInteractor(SetSettingsDataAccessInterface accessObject, SetSettingsOutputBoundary presenter,
                                 UsernameInputData usernameInputData) {
        this.accessObject = accessObject;
        this.settingsPresenter = presenter;
        this.username = usernameInputData.getUsername();
    }

    public void execute(SetSettingsInputData setSettingsInputData) {
        newPassword = setSettingsInputData.getNewPassword();
        newPhoneNumber = setSettingsInputData.getNewPhoneNumber();
        accessObject.get(username).getPassword();
        accessObject.get(username).getPhoneNumber();

        Matcher matcher = pattern.matcher(newPhoneNumber);

        if (!matcher.matches() && !newPhoneNumber.isEmpty()) {
            settingsPresenter.prepareFailView("Phone Number must be in the form XXXXXXXXXX");
            return;
        } else if (newPassword.isEmpty() && !newPhoneNumber.isEmpty()) {
            CommonUser user = new CommonUser(username, oldPassword, LocalDateTime.now(), newPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(oldPassword, newPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        } else if (!newPassword.isEmpty() && newPhoneNumber.isEmpty()) {
            CommonUser user = new CommonUser(username, newPassword, LocalDateTime.now(), oldPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(newPassword, oldPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        } else if (!newPassword.isEmpty()) {
            CommonUser user = new CommonUser(username, newPassword, LocalDateTime.now(), newPhoneNumber);
            accessObject.save(user);
            SetSettingsOutputData outputData = new SetSettingsOutputData(newPassword, newPhoneNumber);
            settingsPresenter.prepareSuccessView(outputData);
            return;
        } else {
            settingsPresenter.prepareSuccessView(new SetSettingsOutputData(newPassword, newPhoneNumber));
            return;
        }
    }

}

