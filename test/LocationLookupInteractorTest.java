import data_access.WeatherRepository;
import entity.Location;
import org.junit.Test;
import use_case.location_lookup.*;

import static org.junit.Assert.*;

public class LocationLookupInteractorTest {
    LocationLookupDataAccessInterface repo;

    public LocationLookupInteractorTest() {
        repo = new WeatherRepository(System.getenv("apiKey"));
    }

    @Test
    public void getNormalLocation() {
        LocationLookupInputData locationData = new LocationLookupInputData("Toronto");

        LocationLookupInputBoundary interactor = getLocationInputBoundary(repo, false, false);

        interactor.execute(locationData);
    }

    @Test
    public void getEmptyLocation() {
        LocationLookupInputData locationData = new LocationLookupInputData("");

        LocationLookupInputBoundary interactor = getLocationInputBoundary(repo, true, true);

        interactor.execute(locationData);
    }

    @Test
    public void getNonExistentLocation() {
        LocationLookupInputData locationData = new LocationLookupInputData("אבחגןחגםשדגגש");

        LocationLookupInputBoundary interactor = getLocationInputBoundary(repo, true, false);

        interactor.execute(locationData);
    }

    private static LocationLookupInputBoundary getLocationInputBoundary(LocationLookupDataAccessInterface repo, boolean isFailureExpected, boolean isEmpty) {
        LocationLookupOutputBoundary presenter = new LocationLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(LocationLookupOutputData rawData) {
                if(isFailureExpected) {
                    fail("Failure was expected");
                }

                Location data = rawData.getLocation();
                assertEquals(data.getName(), "Toronto");
                assertEquals(data.getRegion(), "Ontario");
                assertEquals(data.getCountry(), "Canada");
            }

            @Override
            public void prepareFailView(String error) {
                if(!isFailureExpected) {
                    fail("Use case failure is unexpected.");
                } else if (!isEmpty) {
                    assertNotEquals(error, "Location cannot be empty!");
                } else if(isEmpty) {
                    assertEquals(error, "Location cannot be empty!");
                }
            }
        };

        return new LocationLookupInteractor(repo, presenter);
    }
}
