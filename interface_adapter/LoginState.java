package interface_adapter;

public class LoginState {
    private String username = "";
    private String password = "";
    private String usernameError = null;
    private String passwordError = null;

    // defines copy constructor
    public LoginState(LoginState duplicate) {
        username = duplicate.username;
        password = duplicate.password;
        passwordError = duplicate.passwordError;
        usernameError = duplicate.usernameError;
    }

    public LoginState() {
        // void function body for initializer of default login state
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setUsernameError(String newUsernameError) {
        this.usernameError = newUsernameError;
    }

    public void setPasswordError(String newPasswordError) {
        this.passwordError = newPasswordError;
    }
}
