package use_case.location_lookup;

public class LocationLookupOutputData {
    private final String name;
    private final String region;
    private final String country;
    private final float lon;
    private final float lat;

    public LocationLookupOutputData(String name, String region, String country, float lon, float lat) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.lon = lon;
        this.lat = lat;
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

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }
}
