package use_case.login_signup_switch;

public class LoginSignupSwitchInteractor implements LoginSignupSwitchInputBoundary {
    final LoginSignupSwitchOutputBoundary presenter;

    public LoginSignupSwitchInteractor(LoginSignupSwitchOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(LoginSignupSwitchInputData signupInputData) {
      presenter.prepareSuccessView(new LoginSignupSwitchOutputData(signupInputData.isSwitchToLogin()));
    }
}
