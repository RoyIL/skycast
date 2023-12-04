package interface_adapter.loggedin.notification;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class NotificationViewModel extends ViewModel {
    public final String WINDOW_TITLE = "SkyCast Create Notification";
    public final String TOGGLE_DAILY_MAX_MIN_LABEL = "Toggle Daily Max and Min Temperatures";
    public final String NOTIFICATION_TIME_LABEL = "Time to Notification (Days, Recommended 0.25-7)";
    public final String TOGGLE_PRECIPITATION_CHANCE_LABEL = "Toggle Precipitation Chance";
    public final String TOGGLE_CURRENT_TEMPERATURE_LABEL = "Toggle Current Temperature";
    public final String CITY_NAME_INPUT_LABEL = "City Name";
    public final String TOGGLE_BUTTON_TRUE_STATE = "ON";
    public final String TOGGLE_BUTTON_FALSE_STATE = "OFF";
    public final String CREATE_NOTIFICATION_BUTTON = "Create Notification";
    public final String CANCEL_NOTIFICATION_BUTTON = "Back to Main Menu";

    private NotificationState windowState = new NotificationState();

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public NotificationViewModel() {super("notif");
    }

    public void setWindowState(NotificationState newWindowState) {this.windowState = newWindowState;}

    public NotificationState getWindowState() {return windowState;}

    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);
    }

    public void addPropertyChangedListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }

}
