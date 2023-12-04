package use_case.signup;


public class SignupInputData {
    final private String username;
    final private String password;
    final private String passwordVerification;

    public SignupInputData(String username, String password, String passwordVerification) {
        this.username = username;
        this.password = password;
        this.passwordVerification = passwordVerification;
    }

    String getUsername() { return username;}
    String getPassword() { return password;}
    String getPasswordVerification() { return passwordVerification;}
}
