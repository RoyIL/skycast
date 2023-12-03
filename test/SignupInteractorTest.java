import data_access.FileUserDataAccessObject;
import org.junit.Test;
import use_case.signup.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class SignupInteractorTest {
    public FileUserDataAccessObject repo;

    public SignupInteractorTest() throws IOException {
        repo = new FileUserDataAccessObject(System.currentTimeMillis() + ".csv");
    }

    public void createNormalUser() throws IOException {
        SignupInputData signupData = new SignupInputData("testuser",
                "test", "test");

        SignupInputBoundary interactor = getSignupInputBoundary(repo, false, false);

        interactor.execute(signupData);
    }

    @Test
    public void createUserWithWrongPasswordConfirmation() throws IOException {
        SignupInputData signupData = new SignupInputData("newtest",
                "test", "wrongpass");

        SignupInputBoundary interactor = getSignupInputBoundary(repo, true, false);

        interactor.execute(signupData);
    }

    @Test
    public void createUserTwice() throws IOException {
        SignupInputData signupData = new SignupInputData("testuser",
                "test", "test");

        if(!repo.exists("testuser")) {
            SignupInputBoundary interactor = getSignupInputBoundary(repo, false, false);
            interactor.execute(signupData);
        }

        SignupInputBoundary interactor = getSignupInputBoundary(repo, true, true);

        interactor.execute(signupData);
    }

    private static SignupInputBoundary getSignupInputBoundary(SignupUserDataAccessInterface userRepository, boolean isFailureExpected, boolean shouldUserExist) {
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData data) {
                if(isFailureExpected) {
                    fail("Failure was expected");
                }

                assertEquals("testuser", data.getUsername());
                assertNotNull(data.getCreationTime()); // any creation time is fine.
                assertTrue(userRepository.exists("testuser"));
            }

            @Override
            public void prepareFailView(String error) {
                if(!isFailureExpected) {
                    fail("Use case failure is unexpected.");
                } else if (!shouldUserExist) {
                    assertTrue(!userRepository.exists("newtest"));
                } else if(shouldUserExist) {
                    assertTrue(userRepository.exists("testuser"));
                }
            }
        };

        return new SignupInteractor(userRepository, presenter);
    }
}
