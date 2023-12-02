package use_case.weather_lookup;

public class WeatherLookupOutputData {
    private final float currentTempC;
    private final String currentCondition;
    private final int dailyChanceOfPrecipitation;
    private final float maxDailyTemp;
    private final float minDailyTemp;

    public WeatherLookupOutputData(float currentTempC, String currentCondition, int dailyChanceOfPrecipitation, float maxDailyTemp, float minDailyTemp) {
        this.currentTempC = currentTempC;
        this.currentCondition = currentCondition;
        this.dailyChanceOfPrecipitation = dailyChanceOfPrecipitation;
        this.maxDailyTemp = maxDailyTemp;
        this.minDailyTemp = minDailyTemp;
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
}
