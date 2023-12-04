package use_case.login_signup_switch;

public interface LoginSignupSwitchOutputBoundary {
    void prepareSuccessView(LoginSignupSwitchOutputData user);
    void prepareFailView(String error);
}
