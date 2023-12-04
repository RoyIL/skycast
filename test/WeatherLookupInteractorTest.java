import data_access.WeatherRepository;
import entity.Location;
import entity.LocationWeatherData;
import entity.LocationWeatherForecastData;
import org.junit.Test;
import use_case.weather_lookup.*;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class WeatherLookupInteractorTest {
    WeatherLookupDataAccessInterface repo;

    public WeatherLookupInteractorTest() {
        repo = new WeatherRepository(System.getenv("apiKey"));
    }

    @Test
    public void getNormalLocation() {
        Location location = new Location("Toronto", "Ontario", "Canada", 43.67f, -79.42f);
        WeatherLookupInputData weatherData = new WeatherLookupInputData(location);

        WeatherLookupInputBoundary interactor = getWeatherInputBoundary(repo, false, false);

        interactor.execute(weatherData);
    }

    @Test
    public void getNullLocation() {
        Location location = null;
        WeatherLookupInputData weatherData = new WeatherLookupInputData(location);

        WeatherLookupInputBoundary interactor = getWeatherInputBoundary(repo, true, true);

        interactor.execute(weatherData);
    }

    @Test
    public void getNonExistentLocation() {
        Location location = new Location("אבגחגןגח", "Ontario", "Canada", 432919.67f, 432919.67f);
        WeatherLookupInputData weatherData = new WeatherLookupInputData(location);

        WeatherLookupInputBoundary interactor = getWeatherInputBoundary(repo, true, false);

        interactor.execute(weatherData);
    }

    private static WeatherLookupInputBoundary getWeatherInputBoundary(WeatherLookupDataAccessInterface repo, boolean isFailureExpected, boolean isLocationNull) {
        WeatherLookupOutputBoundary presenter = new WeatherLookupOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherLookupOutputData rawData) {
                if(isFailureExpected) {
                    fail("Failure was expected");
                }

                LocationWeatherData data = rawData.getLocationWeatherData();
                assertFalse(data.getCurrentCondition().isEmpty());
                assertTrue(data.getDailyChanceOfPrecipitation() >= 0 && data.getDailyChanceOfPrecipitation() <= 100);
                //assertTrue(data.getMinDailyTemp() <= data.getCurrentTempC() && data.getCurrentTempC() <= data.getMaxDailyTemp());

                for(LocationWeatherForecastData forecast : data.getWeatherForecast()) {
                    if(forecast != null) {
                        assertTrue(!forecast.getForecastDate().isBefore(LocalDate.now()));
                        assertTrue(forecast.getForecastChanceOfPrecipitation() >= 0 && forecast.getForecastChanceOfPrecipitation() <= 100);
                        assertTrue(forecast.getForecastMinTempC() <= forecast.getForecastMaxTempC());
                    }
                }
            }

            @Override
            public void prepareFailView(String error) {
                if(!isFailureExpected) {
                    fail("Use case failure is unexpected.");
                } else if (!isLocationNull) {
                    assertNotEquals(error, "Location was not provided!");
                } else if(isLocationNull) {
                    assertEquals(error, "Location was not provided!");
                }
            }
        };

        return new WeatherLookupInteractor(repo, presenter);
    }
}
