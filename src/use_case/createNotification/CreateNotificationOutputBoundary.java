package use_case.createNotification;

public interface CreateNotificationOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
