package use_case.createNotification;

import entity.LocationWeatherData;
import use_case.location_lookup.LocationLookupDataAccessInterface;
import use_case.weather_lookup.WeatherLookupDataAccessInterface;
import entity.Location;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNotificationInteractor implements  CreateNotificationInputBoundary{
    final CreateNotificationOutputBoundary presenter;
    final CreateNotificationDataAccessInterface dataAccessObject;
    final LocationLookupDataAccessInterface locationLookupDataAccessObject;
    final WeatherLookupDataAccessInterface weatherLookupDataAccessObject;
    final CreateNotificationServiceInterface serviceCaller;
    final Timer myTimer;

    public CreateNotificationInteractor(CreateNotificationDataAccessInterface dataAccessObject,
                                        LocationLookupDataAccessInterface locationLookupDataAccessObject,
                                        WeatherLookupDataAccessInterface weatherLookupDataAccessObject,
                                        CreateNotificationOutputBoundary presenter,
                                        CreateNotificationServiceInterface serviceCaller) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
        this.locationLookupDataAccessObject = locationLookupDataAccessObject;
        this.weatherLookupDataAccessObject = weatherLookupDataAccessObject;
        this.serviceCaller = serviceCaller;
        myTimer = new Timer("myTimer");
    }

    public void execute(CreateNotificationInputData notificationInputData) {
        long minutesOut = 0;

        Location cityNameHolder = locationLookupDataAccessObject.getLocation(notificationInputData.getCityName());
        String phoneNumber = dataAccessObject.getPhoneNumber(notificationInputData.getUsername());

        if (phoneNumber.equals("NONE")) {
            presenter.prepareFailView("No phone number saved to account!");
            return;
        }

        if (cityNameHolder.getName().isEmpty()) {
            presenter.prepareFailView("This location could not be found!");
            return;
        }

        try {
            double daysOut = Double.parseDouble(notificationInputData.getNotificationTime());
            minutesOut = (long) (daysOut * 24 * 60);

        } catch (Exception EX) {
            presenter.prepareFailView("Not a days out valid number!");
            return;
        }

        TimerTask sendMessage = new TimerTask() {
            @Override
            public void run() {
                String returnMessageAccumulator = "This is your Skycast weather update! \n" +
                        "Location : ";

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

                serviceCaller.sendTwilioMessage(returnMessageAccumulator, phoneNumber);

            }
        };

        myTimer.schedule(sendMessage, 1000 * 60 * minutesOut);

        presenter.prepareSuccessView();
    }



}
