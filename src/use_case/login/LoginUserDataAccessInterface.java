package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean exists(String userId);
    User get(String username);
}
