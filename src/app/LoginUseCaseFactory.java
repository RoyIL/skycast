package app;

import interface_adapter.*;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login_signup_switch.LoginSignupSwitchController;
import interface_adapter.login_signup_switch.LoginSignupSwitchPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.login_signup_switch.LoginSignupSwitchInputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchInteractor;
import use_case.login_signup_switch.LoginSignupSwitchOutputBoundary;
import view.LoginView;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManager, LoginViewModel loginViewModel, LoggedInViewModel loggedinViewModel, LoginUserDataAccessInterface accessObject, SignupViewModel signupViewModel) {
        LoginController loginController = createLoginUseCase(viewManager, loginViewModel, loggedinViewModel, accessObject);
        LoginSignupSwitchController loginSignupSwitchController = createLoginSignupSwitchUseCase(viewManager, loginViewModel, signupViewModel);

        return new LoginView(loginController, loginViewModel, loginSignupSwitchController);
    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManager, LoginViewModel loginViewModel, LoggedInViewModel loggedinViewModel, LoginUserDataAccessInterface accessObject) {
        LoginOutputBoundary outputBoundary = new LoginPresenter(viewManager, loggedinViewModel, loginViewModel);
        LoginInputBoundary inputBoundary = new LoginInteractor(accessObject, outputBoundary);

        return new LoginController(inputBoundary);
    }

    private static LoginSignupSwitchController createLoginSignupSwitchUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        LoginSignupSwitchOutputBoundary outputBoundary = new LoginSignupSwitchPresenter(loginViewModel, signupViewModel, viewManagerModel);
        LoginSignupSwitchInputBoundary inputBoundary = new LoginSignupSwitchInteractor(outputBoundary);

        return new LoginSignupSwitchController(inputBoundary);
    }
}
