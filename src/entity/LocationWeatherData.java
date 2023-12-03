package entity;

public class LocationWeatherData {
    private final float currentTempC;
    private final String currentCondition;
    private final int dailyChanceOfPrecipitation;
    private final float maxDailyTemp;
    private final float minDailyTemp;

    private final LocationWeatherForecastData[] weatherForecast;

    public LocationWeatherData(float currentTempC, String currentCondition, int dailyChanceOfPrecipitation, float maxDailyTemp, float minDailyTemp, LocationWeatherForecastData[] weatherForecast) {
        this.currentTempC = currentTempC;
        this.currentCondition = currentCondition;
        this.dailyChanceOfPrecipitation = dailyChanceOfPrecipitation;
        this.maxDailyTemp = maxDailyTemp;
        this.minDailyTemp = minDailyTemp;
        this.weatherForecast = weatherForecast;
    }

    public float getCurrentTempC() {
        return currentTempC;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public int getDailyChanceOfPrecipitation() {
        return dailyChanceOfPrecipitation;
    }

    public float getMaxDailyTemp() {
        return maxDailyTemp;
    }

    public float getMinDailyTemp() {
        return minDailyTemp;
    }

    public LocationWeatherForecastData[] getWeatherForecast() {
        return weatherForecast;
    }
}