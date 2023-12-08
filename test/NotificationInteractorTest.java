import org.junit.Test;
import use_case.notification.*;

import java.io.IOException;

public class NotificationInteractorTest {
    @Test
    public void NotificationInteractorTest() throws IOException {
        NotificationInputBoundary interactor = getNotificationInputBoundary();

        interactor.execute(new NotificationInputData("Testusername"));
    }

    private static NotificationInputBoundary getNotificationInputBoundary() {
        NotificationOutputBoundary presenter = new NotificationOutputBoundary() {
            @Override
            public void prepareSuccessView(NotificationOutputData notification) {

            }
        };

        return new NotificationInteractor(presenter);
    }

}
