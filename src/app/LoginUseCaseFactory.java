package app;

import interface_adapter.*;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManager, LoginViewModel loginViewModel, LoggedInViewModel loggedinViewModel, LoginUserDataAccessInterface accessObject) {
        LoginController loginController = createLoginUseCase(viewManager, loginViewModel, loggedinViewModel, accessObject);
        return new LoginView(loginController, loginViewModel);
    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManager, LoginViewModel loginViewModel, LoggedInViewModel loggedinViewModel, LoginUserDataAccessInterface accessObject) {
        LoginOutputBoundary outputBoundary = new LoginPresenter(viewManager, loggedinViewModel, loginViewModel);
        LoginInputBoundary inputBoundary = new LoginInteractor(accessObject, outputBoundary);

        return new LoginController(inputBoundary);
    }
}
