package interface_adapter.login_signup_switch;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.location_lookup.LocationLookupOutputBoundary;
import use_case.location_lookup.LocationLookupOutputData;
import use_case.login_signup_switch.LoginSignupSwitchOutputBoundary;
import use_case.login_signup_switch.LoginSignupSwitchOutputData;

public class LoginSignupSwitchPresenter implements LoginSignupSwitchOutputBoundary {
    final LoginViewModel logInViewModel;
    final SignupViewModel signUpViewModel;
    final ViewManagerModel viewManagerModel;

    public LoginSignupSwitchPresenter(LoginViewModel logInViewModel, SignupViewModel signUpViewModel, ViewManagerModel viewManagerModel) {
        this.logInViewModel = logInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signUpViewModel = signUpViewModel;
    }

    @Override
    public void prepareSuccessView(LoginSignupSwitchOutputData data) {
        logInViewModel.getWindowState().setUsername("");
        logInViewModel.getWindowState().setPassword("");
        logInViewModel.firePropertyChanged();

        signUpViewModel.getWindowState().setUsername("");
        signUpViewModel.getWindowState().setPassword("");
        signUpViewModel.getWindowState().setRepPassword("");
        signUpViewModel.firePropertyChanged();

        if(data.isSwitchToLogin()) {
            viewManagerModel.setActiveView(logInViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else {
            viewManagerModel.setActiveView(signUpViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void prepareFailView(String error) {
    }
}
