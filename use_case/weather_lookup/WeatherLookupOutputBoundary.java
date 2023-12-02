package use_case.weather_lookup;

public interface WeatherLookupOutputBoundary {
    void prepareSuccessView(WeatherLookupOutputData user);
    void prepareFailView(String error);
}
