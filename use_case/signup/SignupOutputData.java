package use_case.signup;

public class SignupOutputData {
    private final String username;
    private final String creationTime;

    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
