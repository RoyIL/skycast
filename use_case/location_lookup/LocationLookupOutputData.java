package use_case.location_lookup;

public class LocationLookupOutputData {
    private final String name;
    private final String region;
    private final String country;

    public LocationLookupOutputData(String name, String region, String country) {
        this.name = name;
        this.region = region;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }
}
