package use_case.createNotification;

import entity.LocationWeatherData;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.notifacation.NotificationInputData;
import use_case.notifacation.NotificationOutputBoundary;
import use_case.notifacation.NotificationOutputData;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;
import entity.Location;

public class CreateNotificationInteractor {
    final CreateNotificationOutputBoundary presenter;
    final CreateNotificationDataAccessInterface twilioDataAccessObject;
    final LocationLookupDataAccessInterface locationLookupDataAccessObject;
    final WeatherLookupDataAccessInterface weatherLookupDataAccessObject;

    public CreateNotificationInteractor(CreateNotificationDataAccessInterface dataAccessObject,
                                        LocationLookupDataAccessInterface locationLookupDataAccessObject,
                                        WeatherLookupDataAccessInterface weatherLookupDataAccessObject,
                                        CreateNotificationOutputBoundary presenter) {
        this.presenter = presenter;
        this.twilioDataAccessObject = dataAccessObject;
        this.locationLookupDataAccessObject = locationLookupDataAccessObject;
        this.weatherLookupDataAccessObject = weatherLookupDataAccessObject;
    }

    public void execute(CreateNotificationInputData notificationInputData) {
        String returnMessageAccumulator = "This is your Skycast weather update! \n" +
                "Location : ";

        Location cityNameHolder = locationLookupDataAccessObject.getLocation(notificationInputData.getCityName());

        if (cityNameHolder.getName().isEmpty()) {
            presenter.prepareFailView("This location could not be found!");
            return;
        }

        returnMessageAccumulator += cityNameHolder.getName() + ", " + cityNameHolder.getRegion() + ", " +
                cityNameHolder.getCountry() + "\n";

        LocationWeatherData weatherDataStorage = weatherLookupDataAccessObject.getWeather(cityNameHolder);

        if (notificationInputData.getWantCurrentTemperature()) {
            returnMessageAccumulator += "Current Temperature: " + weatherDataStorage.getCurrentTempC() +
                    " degrees celsius \n";
        }

        if (notificationInputData.getWantDailyMaxMin()) {
            returnMessageAccumulator += "Daily Max Temperature: " + weatherDataStorage.getMaxDailyTemp() +
                    " degrees celsius \n" + "Daily Min Temperature: " + weatherDataStorage.getMinDailyTemp() + " " +
                    "degrees celsius \n";
        }

        if (notificationInputData.getWantPrecipitationChance()) {
            returnMessageAccumulator += "Precipitation Chance: " + weatherDataStorage.getDailyChanceOfPrecipitation() +
                    "% \n";
        }

        presenter.prepareSuccessView(new CreateNotificationOutputData(returnMessageAccumulator));
    }
}
