package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.loggedin.*;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import view.LoggedInView;

import java.time.LocalDateTime;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        // response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))); #is this needed?

        SignupState signupState = signupViewModel.getWindowState();
        signupState.setUsername(response.getUsername());

        LoggedInState updatedState = loggedInViewModel.getWindowState();
        updatedState.setUsername(signupState.getUsername());
        loggedInViewModel.setWindowState(updatedState);
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getWindowState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
