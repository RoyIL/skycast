package interface_adapter;

import use_case.LoginInputBoundary;
import use_case.LoginInputData;


public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInoutData(username, password);
    }
}
