import data_access.WeatherRepository;
import entity.Location;
import org.junit.Test;
import use_case.location_lookup.*;
import use_case.login_signup_switch.*;

import static org.junit.Assert.*;

public class LoginSignupSwitchTest {
    @Test
    public void switchToSignup() {
        LoginSignupSwitchInputBoundary interactor = getLoginSignupInputBoundary();

        interactor.execute(new LoginSignupSwitchInputData(true));
    }

    private static LoginSignupSwitchInputBoundary getLoginSignupInputBoundary() {
        LoginSignupSwitchOutputBoundary presenter = new LoginSignupSwitchOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginSignupSwitchOutputData rawData) {
                if(rawData.isSwitchToLogin()) {
                    //test views
                }
            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        return new LoginSignupSwitchInteractor(presenter);
    }
}
