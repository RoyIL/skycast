package interface_adapter.login_signup_switch;

import use_case.location_lookup.LocationLookupInputBoundary;
import use_case.location_lookup.LocationLookupInputData;
import use_case.login_signup_switch.LoginSignupSwitchInputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchInputData;

public class LoginSignupSwitchController {
    final LoginSignupSwitchInputBoundary useCaseInteractor;

    public LoginSignupSwitchController(LoginSignupSwitchInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void execute(boolean switchToLogin) {
        useCaseInteractor.execute(new LoginSignupSwitchInputData(switchToLogin));
    }
}
