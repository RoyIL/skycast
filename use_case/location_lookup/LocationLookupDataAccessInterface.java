package use_case.location_lookup;

import entity.Location;

public interface LocationLookupDataAccessInterface {
    Location getLocation(String locationInput);
}
