package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String password = "";
    private String repPassword = "";
    private String usernameError = null;
    private String passwordError = null;
    private String repPasswordError = null;

    public SignupState(SignupState duplicate) {
        username = duplicate.username;
        password = duplicate.password;
        repPassword = duplicate.repPassword;
        usernameError = duplicate.usernameError;
        passwordError = duplicate.passwordError;
        repPasswordError = duplicate.repPasswordError;
    }

    public SignupState() {
        // void initializer body to differentiate from copy function
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepPassword() {
        return repPassword;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepPasswordError() {
        return repPasswordError;
    }
    public void setUsername(String newUsername) {
         username = newUsername;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void setRepPassword(String newRepPassword) {
        repPassword = newRepPassword;
    }

    public void setUsernameError(String newUsernameError) {
        usernameError = newUsernameError;
    }

    public void setPasswordError(String newPasswordError) {
        passwordError = newPasswordError;
    }

    public void setRepPasswordError(String newRepPasswordError) {
        repPasswordError = newRepPasswordError;
    }
}
