package entity;

import java.time.LocalDate;
import java.util.Date;

public class LocationWeatherForecastData {
    private final LocalDate forecastDate;
    private final float forecastMaxTempC;
    private final float forecastMinTempC;
    private final int forecastChanceOfPrecipitation;

    public LocationWeatherForecastData(LocalDate forecastDate, float forecastMaxTempC, float forecastMinTempC, int forecastChanceOfPrecipitation) {
        this.forecastDate = forecastDate;
        this.forecastMaxTempC = forecastMaxTempC;
        this.forecastMinTempC = forecastMinTempC;
        this.forecastChanceOfPrecipitation = forecastChanceOfPrecipitation;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public float getForecastMaxTempC() {
        return forecastMaxTempC;
    }

    public float getForecastMinTempC() {
        return forecastMinTempC;
    }

    public int getForecastChanceOfPrecipitation() {
        return forecastChanceOfPrecipitation;
    }
}
