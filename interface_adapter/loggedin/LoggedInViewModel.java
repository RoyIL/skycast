package interface_adapter.loggedin;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    /**
     * NOTICE: ALL STRING NAMES ARE TEMPORARY AND ONLY SERVE FOR TESTING FUNCTIONALITY OF
     * OUR FIRST USE CASE. THESE WILL BE UPDATED AS THE PROJECT PROGRESSES
     */
    public final String TITLE_BOX = "Logged In View";

    public final String APPLICATION_TITLE = "SkyCast";
    public final String CITY_SEARCH_DEFAULT_TEXT = "Enter city name here...";
    public final String CITY_SEARCH_BUTTON_LABEL= "Search City";
    public final String CREATE_NOTIFICATION_BUTTON_LABEL = "Create Notification";
    public final String CURRENT_TEMPERATURE_DEFAULT_TEXT = "00";
    public final String DAILY_MAX_MIN_DEFAULT_TEXT = "(00:00)";
    public final String DAILY_MAX_MIN_LABEL = "(MAX:MIN) Degrees Celsius";
    public final String PRECIPITATION_CHANCE_LABEL = "Chance of Percipitation";
    public final String PRECIPITATION_CHANCE_DEFAULT_TEXT= "00%";
    public final String CURRENT_TEMPERATURE_LABEL = "Current Temperature (Degrees Celsius)";
    public final String FUTURE_DATA_DEFAULT_TEXT = "(0: 00:00, 00%)";
    // TODO: CREATE PROPER LABEL FOR FUTURE_DATA_LABEL
    public final String FUTURE_DATA_LABEL = "TEMPTITLE (Days Out: MAX:MIN, % Rain)";

    // TODO: Add valid File path for image icons
    public final ImageIcon SETTING_BUTTON_IMG = new ImageIcon("");
    public final ImageIcon DEFAULT_DISPLAY_IMG = new ImageIcon("");

    private LoggedInState windowState = new LoggedInState();

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public LoggedInViewModel() {
        super("logged in");
    }

    public void setWindowState(LoggedInState newWindowState) {
        this.windowState = newWindowState;
    }

    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);
    }

    public void addPropertyChangedListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }

    public LoggedInState getWindowState() {
        return windowState;
    }
}
