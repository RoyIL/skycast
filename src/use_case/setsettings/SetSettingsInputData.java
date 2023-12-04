package use_case.setsettings;


public class SetSettingsInputData {

    final private String newPassword;
    final private String newPhoneNumber;

    public SetSettingsInputData(String newPassword, String newPhoneNumber) {
        this.newPassword = newPassword;
        this.newPhoneNumber = newPhoneNumber;

    }

    public String getNewPassword() {
        return this.newPassword;
    }
    public String getNewPhoneNumber() {
        return this.newPhoneNumber;
    }
}
