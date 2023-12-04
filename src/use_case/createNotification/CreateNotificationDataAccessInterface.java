package use_case.createNotification;

import entity.Location;
import entity.LocationWeatherData;

public interface CreateNotificationDataAccessInterface {
    String getPhoneNumber(String username);
}
