package app;

import interface_adapter.*;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login_signup_switch.LoginSignupSwitchController;
import interface_adapter.login_signup_switch.LoginSignupSwitchPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login_signup_switch.LoginSignupSwitchInputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchInteractor;
import use_case.login_signup_switch.LoginSignupSwitchOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface accessObject, LoggedInViewModel loggedInViewModel) {
        SignupController signupController = createSignupUseCase(viewManagerModel, loginViewModel, signupViewModel, accessObject, loggedInViewModel);
        LoginSignupSwitchController loginSignupSwitchController = createLoginSignupSwitchUseCase(viewManagerModel, loginViewModel, signupViewModel);

        return new SignupView(signupController, signupViewModel, loginSignupSwitchController);
    }

    private static SignupController createSignupUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface accessObject, LoggedInViewModel loggedInViewModel) {
        SignupOutputBoundary outputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, loggedInViewModel);
        SignupInputBoundary inputBoundary = new SignupInteractor(accessObject, outputBoundary);

        return new SignupController(inputBoundary);
    }

    private static LoginSignupSwitchController createLoginSignupSwitchUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        LoginSignupSwitchOutputBoundary outputBoundary = new LoginSignupSwitchPresenter(loginViewModel, signupViewModel, viewManagerModel);
        LoginSignupSwitchInputBoundary inputBoundary = new LoginSignupSwitchInteractor(outputBoundary);

        return new LoginSignupSwitchController(inputBoundary);
    }
}
