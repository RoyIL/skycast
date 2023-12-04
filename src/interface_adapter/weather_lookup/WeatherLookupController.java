package interface_adapter.weather_lookup;

import entity.Location;
import use_case.weather_lookup.WeatherLookupInputBoundary;
import use_case.weather_lookup.WeatherLookupInputData;

public class WeatherLookupController {
    final WeatherLookupInputBoundary useCaseInteractor;

    public WeatherLookupController(WeatherLookupInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void execute(Location location) {
        useCaseInteractor.execute(new WeatherLookupInputData(location));
    }
}
