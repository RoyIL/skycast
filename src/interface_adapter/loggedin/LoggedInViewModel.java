package interface_adapter.loggedin;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    // Constant declaration for LoggedInView

    // String declaration
    public final String APPLICATION_TITLE = "SkyCast";
    public final String CITY_SEARCH_DEFAULT_TEXT = "Enter city name here...";
    public final String CITY_SEARCH_BUTTON_LABEL= "Search City";
    public final String CREATE_NOTIFICATION_BUTTON_LABEL = "Create Notification";
    public final String CURRENT_TEMPERATURE_DEFAULT_TEXT = "00";
    public final String DAILY_MAX_MIN_DEFAULT_TEXT = "(00:00)";
    public final String DAILY_MAX_MIN_LABEL = "(MIN:MAX) Degrees Celsius";
    public final String PRECIPITATION_CHANCE_LABEL = "Chance of Precipitation";
    public final String PRECIPITATION_CHANCE_DEFAULT_TEXT= "00%";
    public final String CURRENT_TEMPERATURE_LABEL = "Current Temperature (Degrees Celsius)";
    public final String FUTURE_DATA_DEFAULT_TEXT = "(0: 00:00, 00%)";
    public final String FUTURE_DATA_LABEL = "Forecast (Days Out: MIN:MAX, % Precipitation)";
    public final String LOGOUT_BUTTON_TEXT = "Logout";
    public final String BUFFER_EMPTY_TEXT = "";

    // ImageIcon declaration
    public final ImageIcon SETTING_BUTTON_IMG = scaleImageTo50("images/setting_icon.png");
    public final ImageIcon DEFAULT_DISPLAY_IMG = scaleImageTo50("src/images/sunny_icon.png");
    public final ImageIcon SUNNY_IMG = scaleImageTo50("src/images/sunny_icon.png");
    public final ImageIcon RAINY_IMG = scaleImageTo50("src/images/rainy_icon.png");
    public final ImageIcon SNOWY_IMG = scaleImageTo50("src/images/snowy_icon.png");
    public final ImageIcon CLOUDY_IMG = scaleImageTo50("src/images/cloudy_icon.png");

    // Font declaration
    public final Font TITLE_FONT = new Font("TITLE_FONT", Font.PLAIN,30);

    // Creation of LoggedInState for tracking variables in the view
    private LoggedInState windowState = new LoggedInState();

    // Facilitates firePropertyChange
    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    // Calls superclass initializer
    public LoggedInViewModel() {
        super("logged in");
    }

    // Method for updating window state
    public void setWindowState(LoggedInState newWindowState) {
        this.windowState = newWindowState;
    }

    // Lets assigned listeners know that the windowState has changed
    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);
    }

    // Adds listener for firePropertyChanged
    public void addPropertyChangedListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }

    // Getter method for window state
    public LoggedInState getWindowState() {
        return windowState;
    }

    // Private helper method for resizing images
    private ImageIcon scaleImageTo50(String filePath) {
        /*
          This is a helper function to scale images to fit the view.
          This function takes a file path and returns an ImageIcon of 50*50 size
         */
        // loads image from filepath to ImageIcon
        ImageIcon tempImg = new ImageIcon(filePath);
        // Converts image icon to Image that can be re sized
        Image convertImg = tempImg.getImage();
        // Creates new image based on scaled Image
        Image newConvertImage = convertImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        // Returns the scaled image as an ImageIcon
        return new ImageIcon(newConvertImage);
    }
}
