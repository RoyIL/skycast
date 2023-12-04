package interface_adapter.weather_lookup;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.location_lookup.LocationLookupOutputBoundary;
import use_case.location_lookup.LocationLookupOutputData;
import use_case.weather_lookup.WeatherLookupOutputBoundary;
import use_case.weather_lookup.WeatherLookupOutputData;

public class WeatherLookupPresenter implements WeatherLookupOutputBoundary {
    final LoggedInViewModel loggedInViewModel;
    final ViewManagerModel viewManagerModel;

    public WeatherLookupPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(WeatherLookupOutputData data) {
        LoggedInState loggedinState = loggedInViewModel.getWindowState();
        loggedinState.setLocationWeatherData(data.getLocationWeatherData());
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedinState = loggedInViewModel.getWindowState();
        loggedinState.setLocationWeatherData(null);
        loggedInViewModel.firePropertyChanged();
    }
}
