package entity;

public class Location {
    private final String name;
    private final String region;
    private final String country;
    private final float lat;
    private final float lon;

    public Location(String name, String region, String country, float lat, float lon) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
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

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
