package interface_adapter.location_lookup;

import use_case.location_lookup.LocationLookupInputBoundary;
import use_case.location_lookup.LocationLookupInputData;

public class LocationLookupController {
    final LocationLookupInputBoundary useCaseInteractor;

    public LocationLookupController(LocationLookupInputBoundary useCaseInteractor) {
        this.useCaseInteractor = useCaseInteractor;
    }

    public void execute(String locationInput) {
        useCaseInteractor.execute(new LocationLookupInputData(locationInput));
    }
}
