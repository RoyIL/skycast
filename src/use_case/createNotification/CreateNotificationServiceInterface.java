package use_case.createNotification;

public interface CreateNotificationServiceInterface {
    void sendTwilioMessage(String message, String phoneNumber);
}
