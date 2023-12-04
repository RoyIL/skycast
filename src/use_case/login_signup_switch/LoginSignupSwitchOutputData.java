package use_case.login_signup_switch;

public class LoginSignupSwitchOutputData {
    final private boolean switchToLogin;

    public LoginSignupSwitchOutputData(boolean switchToLogin) {
        this.switchToLogin = switchToLogin;
    }

    public boolean isSwitchToLogin() {
        return switchToLogin;
    }
}
