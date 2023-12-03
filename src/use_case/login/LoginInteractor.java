package use_case.login;

import entity.CommonUser;
import entity.User;

import java.time.LocalDateTime;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface accessObject;
    final LoginOutputBoundary presenter;

    public LoginInteractor(LoginUserDataAccessInterface accessObject, LoginOutputBoundary presenter) {
        this.accessObject = accessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(LoginInputData signupInputData) {
        if(!accessObject.exists(signupInputData.getUsername())) {
            presenter.prepareFailView("This account does not exist!");
            return;
        }

        User user = accessObject.get(signupInputData.getUsername());

        if(!signupInputData.getPassword().equals(user.getPassword())) {
            presenter.prepareFailView("Wrong password!");
            return;
        }

        presenter.prepareSuccessView(new LoginOutputData(user.getUsername()));
    }
}
