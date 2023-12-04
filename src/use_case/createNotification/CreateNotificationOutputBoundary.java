package use_case.createNotification;

public interface CreateNotificationOutputBoundary {
    void prepareSuccessView(CreateNotificationOutputData successMessage);
    void prepareFailView(String error);
}
