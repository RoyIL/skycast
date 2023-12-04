package interface_adapter.location_lookup;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.location_lookup.LocationLookupOutputBoundary;
import use_case.location_lookup.LocationLookupOutputData;

public class LocationLookupPresenter implements LocationLookupOutputBoundary {
    final LoggedInViewModel loggedInViewModel;
    final ViewManagerModel viewManagerModel;

    public LocationLookupPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LocationLookupOutputData data) {
        LoggedInState loggedinState = loggedInViewModel.getWindowState();
        loggedinState.setCurrentRetrievedLocation(data.getLocation());
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedinState = loggedInViewModel.getWindowState();
        loggedinState.setCurrentRetrievedLocation(null);
        loggedInViewModel.firePropertyChanged();
    }
}
