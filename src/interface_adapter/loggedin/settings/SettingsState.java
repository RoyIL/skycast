package interface_adapter.loggedin.settings;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
public class SettingsState {

    private String username = "";
    private String password = "";
    private String phoneNumber = "";
    private String region = "";
    private String usernameError = null;
    private String passwordError = null;
    private String phoneNumberError = null;
    private String regionError = null;


    public SettingsState(SettingsState duplicate) {
        username = duplicate.username;
        password = duplicate.password;
        phoneNumber = duplicate.phoneNumber;
        region = duplicate.region;
        usernameError = duplicate.usernameError;
        passwordError = duplicate.passwordError;
        phoneNumberError = duplicate.phoneNumberError;
        regionError = duplicate.regionError;

    }

    public SettingsState() {}

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getRegion() {
        return region;
    }

    public String getUsernameError() {
        return usernameError;
    }
    public String getPasswordError() {
        return passwordError;
    }
    public String getPhoneNumberError() {
        return phoneNumberError;
    }
    public String getRegionError() {
        return regionError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public void setRegionError(String regionError) {
        this.regionError = regionError;
    }
}

