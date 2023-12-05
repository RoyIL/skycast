package interface_adapter.loggedin.settings;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
public class SettingsState {

    private String username = "";
    private String password = "";
    private String phoneNumber = "";
    private String usernameError = null;
    private String passwordError = null;
    private String phoneNumberError = null;

    public SettingsState(SettingsState duplicate) {
        username = duplicate.username;
        password = duplicate.password;
        phoneNumber = duplicate.phoneNumber;
        usernameError = duplicate.usernameError;
        passwordError = duplicate.passwordError;
        phoneNumberError = duplicate.phoneNumberError;
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

    public String getUsernameError() {
        return usernameError;
    }
    public String getPhoneNumberError() {
        return phoneNumberError;
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

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

}

