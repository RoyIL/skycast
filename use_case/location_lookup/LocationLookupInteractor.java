package use_case.location_lookup;

import entity.Location;

public class LocationLookupInteractor implements LocationLookupInputBoundary {
    final LocationLookupDataAccessInterface accessObject;
    final LocationLookupOutputBoundary presenter;

    public LocationLookupInteractor(LocationLookupDataAccessInterface accessObject, LocationLookupOutputBoundary presenter) {
        this.accessObject = accessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(LocationLookupInputData locationLookupInputData) {
        if(locationLookupInputData.getLocation().isEmpty()) {
            presenter.prepareFailView("Location cannot be empty!");
            return;
        }

        Location location = accessObject.getLocation(locationLookupInputData.getLocation());

        if(location == null) {
            presenter.prepareFailView("Cannot find the specified location.");
            return;
        }

        presenter.prepareSuccessView(new LocationLookupOutputData(location.getName(), location.getRegion(), location.getCountry(), location.getLon(), location.getLat()));
    }
}
