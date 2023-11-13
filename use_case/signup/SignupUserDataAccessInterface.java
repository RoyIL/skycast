package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean exists(String userId);
    void save( User user);
}
