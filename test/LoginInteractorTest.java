import data_access.FileUserDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import use_case.login.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoginInteractorTest {
    LoginUserDataAccessInterface repo;

    @Before
    public void createUser() throws IOException {
        SignupInteractorTest signupTest = new SignupInteractorTest();
        signupTest.createNormalUser();

        repo = new FileUserDataAccessObject(signupTest.repo.getCsvFilePath());
    }

    @Test
    public void loginNormalUser() throws IOException {
        LoginInputData loginData = new LoginInputData("testuser",
                "test");

        LoginInputBoundary interactor = getLoginInputBoundary(repo, false, false);

        interactor.execute(loginData);
    }

    @Test
    public void loginWithWrongPassword() throws IOException {
        LoginInputData loginData = new LoginInputData("testuser",
                "wrongpass");

        LoginInputBoundary interactor = getLoginInputBoundary(repo, true, true);

        interactor.execute(loginData);
    }

    @Test
    public void loginWithWrongUser() throws IOException {
        LoginInputData loginData = new LoginInputData("doesnotexist",
                "123");

        LoginInputBoundary interactor = getLoginInputBoundary(repo, true, false);

        interactor.execute(loginData);
    }

    private static LoginInputBoundary getLoginInputBoundary(LoginUserDataAccessInterface userRepository, boolean isFailureExpected, boolean shouldUserExist) {
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData data) {
                if(isFailureExpected) {
                    fail("Failure was expected");
                }

                assertEquals(data.getUsername(), "testuser");
            }

            @Override
            public void prepareFailView(String error) {
                if(!isFailureExpected) {
                    fail("Use case failure is unexpected.");
                } else if (!shouldUserExist) {
                    assertTrue(!userRepository.exists("doesnotexist"));
                } else if(shouldUserExist) {
                    assertTrue(userRepository.exists("testuser"));
                }
            }
        };

        return new LoginInteractor(userRepository, presenter);
    }
}
