package use_case.weather_lookup;

import entity.LocationWeatherData;

public class WeatherLookupInteractor implements WeatherLookupInputBoundary {
    final WeatherLookupDataAccessInterface accessObject;
    final WeatherLookupOutputBoundary presenter;

    public WeatherLookupInteractor(WeatherLookupDataAccessInterface accessObject, WeatherLookupOutputBoundary presenter) {
        this.accessObject = accessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(WeatherLookupInputData weatherLookupInputData) {
        if(weatherLookupInputData.getLocation() == null) {
            presenter.prepareFailView("Location was not provided!");
            return;
        }

        LocationWeatherData weatherData = accessObject.getWeather(weatherLookupInputData.getLocation());

        if(weatherData == null) {
            presenter.prepareFailView("Could not access weather data for the specified location.");
            return;
        }

        presenter.prepareSuccessView(new WeatherLookupOutputData(weatherData.getCurrentTempC(),
                weatherData.getCurrentCondition(), weatherData.getDailyChanceOfPrecipitation(), weatherData.getMaxDailyTemp(),
                weatherData.getMinDailyTemp()));
    }
}
