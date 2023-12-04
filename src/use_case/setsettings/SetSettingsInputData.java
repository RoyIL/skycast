package use_case.setsettings;


public class SetSettingsInputData {

    final private String newPassword;
    final private String newPhoneNumber;
    final private String username;

    public SetSettingsInputData(String username, String newPassword, String newPhoneNumber) {
        this.newPassword = newPassword;
        this.newPhoneNumber = newPhoneNumber;
        this.username = username;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
    public String getNewPhoneNumber() {
        return this.newPhoneNumber;
    }
    public String getUsername() {return this.username;}
}
