package use_case.signup;

import entity.CommonUser;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface accessObject;
    final SignupOutputBoundary presenter;

    public SignupInteractor(SignupUserDataAccessInterface accessObject, SignupOutputBoundary presenter) {
        this.accessObject = accessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if(accessObject.exists(signupInputData.getUsername())) {
            presenter.prepareFailView("There is already a user with the corresponding username in the system!");
            return;
        }

        if(!signupInputData.getPassword().equals(signupInputData.getPasswordVerification())) {
            presenter.prepareFailView("Passwords do not match!");
            return;
        }

        CommonUser user = new CommonUser(signupInputData.getUsername(), signupInputData.getPassword(), LocalDateTime.now());
        accessObject.save(user);

        presenter.prepareSuccessView(new SignupOutputData(user.getUsername(), user.getCreationTime().toString()));
    }
}
