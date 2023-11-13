package entity;

import java.time.LocalDateTime;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private final LocalDateTime creationTime;
    public CommonUser(String inpUsername, String inpPassword, LocalDateTime inpCreationTime) {
        username = inpUsername;
        password = inpPassword;
        creationTime = inpCreationTime;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }
}
