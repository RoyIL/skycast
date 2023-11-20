package app;

import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface accessObject) {
        SignupController signupController = createSignupUseCase(viewManagerModel, loginViewModel, signupViewModel, accessObject);
        return new SignupView(signupController, signupViewModel);
    }

    private static SignupController createSignupUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface accessObject) {
        SignupOutputBoundary outputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary inputBoundary = new SignupInteractor(accessObject, outputBoundary);

        return new SignupController(inputBoundary);
    }
}
