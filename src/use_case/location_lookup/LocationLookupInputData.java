package use_case.location_lookup;

public class LocationLookupInputData {
    final private String location;

    public LocationLookupInputData(String location) {
        this.location = location;
    }

    String getLocation() {
        return location;
    }
}
