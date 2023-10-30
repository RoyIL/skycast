package interface_adapter;

import use_case.LoginOutputBoundary;
import use_case.LoginOutputData;
import interface_adapter.LoggedInState;
import interface_adapter.LoggedInViewModel;
import interface_adapter.ViewManagerModel;


public class LoginPresenter implements LoginOutputBoumdary {

    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel logInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = logInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData output) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(output.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}

