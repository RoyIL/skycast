package interface_adapter.loggedin;

public class LoggedInState {
    private String username = "";
    public LoggedInState(LoggedInState duplicate) {
        username = duplicate.username;
    }
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
