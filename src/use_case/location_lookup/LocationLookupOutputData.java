package use_case.location_lookup;

import entity.Location;

public class LocationLookupOutputData {
    private final Location location;

    public LocationLookupOutputData(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
