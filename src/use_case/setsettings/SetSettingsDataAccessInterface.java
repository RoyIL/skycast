package use_case.setsettings;

import entity.User;

public interface SetSettingsDataAccessInterface {

    void save(User user);

    User get(String username);

}
