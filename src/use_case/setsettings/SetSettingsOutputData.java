package use_case.setsettings;

public class SetSettingsOutputData {

    private final String newPassword;
    private final String newPhoneNumber;

    public SetSettingsOutputData(String newPassword, String newPhoneNumber) {
        this.newPassword = newPassword;
        this.newPhoneNumber = newPhoneNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }
}
