import data_access.FileUserDataAccessObject;
import data_access.WeatherRepository;
import entity.CommonUser;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import services.TwilioService;
import use_case.createNotification.*;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;


public class CreateNotifcationInteractorTest {

    public FileUserDataAccessObject repo;

    public CreateNotifcationInteractorTest() throws IOException {
        repo = new FileUserDataAccessObject(System.currentTimeMillis() + ".csv");
    }

    @Test
    public void createSimpleNotificationTest() throws IOException {
        CreateNotificationDataAccessInterface createNotificationDataObj = getCreateNotificationDataAccessInterface();
        WeatherLookupDataAccessInterface weatherLookupDataObj = getWeatherLookupDataAccessInterface();
        LocationLookupDataAccessInterface locationLookupDataObj = getLocationLookupDataAccessInterface();

        CreateNotificationServiceInterface twilioInterface = getTwilioInterface();

        CreateNotificationInputBoundary interactor = getCreateNotificationInputBoundary(createNotificationDataObj,
                weatherLookupDataObj, locationLookupDataObj, twilioInterface);

        interactor.execute(new CreateNotificationInputData("Toronto", true,
                true, true, "0", "Testuser"));
    }

    @Before
    public void createTestUser() {
        LocalDateTime testDateTime = LocalDateTime.of(2023, 12, 8, 7, 15, 0, 0);

        User testUser = new CommonUser("Testuser", "test", testDateTime, "2268689717");

        repo.save(testUser);
    }

    private static CreateNotificationInputBoundary getCreateNotificationInputBoundary(CreateNotificationDataAccessInterface cNDAI,
                                                                                      WeatherLookupDataAccessInterface wLDAI,
                                                                                      LocationLookupDataAccessInterface lLDAI,
                                                                                      CreateNotificationServiceInterface twilioInterface) {
        CreateNotificationOutputBoundary presenter = new CreateNotificationOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        return new CreateNotificationInteractor(cNDAI, lLDAI, wLDAI, presenter, twilioInterface);
    }

    private CreateNotificationDataAccessInterface getCreateNotificationDataAccessInterface() {
        CreateNotificationDataAccessInterface dataAccessObject = new CreateNotificationDataAccessInterface() {
            @Override
            public String getPhoneNumber(String username) {
                return repo.getPhoneNumber("Testuser");
            }
        };

        return dataAccessObject;
    }

    private LocationLookupDataAccessInterface getLocationLookupDataAccessInterface() {
        return new WeatherRepository(System.getenv("apiKey"));
    }

    private WeatherLookupDataAccessInterface getWeatherLookupDataAccessInterface() {
        return new WeatherRepository(System.getenv("apiKey"));
    }

    private CreateNotificationServiceInterface getTwilioInterface() {
        return new TwilioService();
    }

}
