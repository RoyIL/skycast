package use_case.login_signup_switch;

public class LoginSignupSwitchInputData {
    final private boolean switchToLogin;

    public LoginSignupSwitchInputData(boolean switchToLogin) {
        this.switchToLogin = switchToLogin;
    }

    public boolean isSwitchToLogin() {
        return switchToLogin;
    }
}
