package interface_adapter;

import use_case.LoginOutputBoundary;
import use_case.LoginOutputData;
import interface_adapter.LoggedInState;
import interface_adapter.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData output) {
        LoggedInState loggedInState = loggedInViewModel.getWindowState();
        loggedInState.setUsername(output.getUsername());
        this.loggedInViewModel.setWindowState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getWindowState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}

