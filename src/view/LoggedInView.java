package view;

import entity.Location;
import entity.LocationWeatherData;
import entity.LocationWeatherForecastData;
import interface_adapter.location_lookup.LocationLookupController;
import interface_adapter.loggedin.*;
import interface_adapter.loggedin.settings.SettingsButtonController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.loggedin.notification.NotificationController;
import interface_adapter.login_signup_switch.LoginSignupSwitchController;
import interface_adapter.weather_lookup.WeatherLookupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    // Variable declaration - do not modify TODO: sort declaration by data types

    private final JLabel applicationTitle;

    private final JTextField citySearchInputField;
    private final JButton createNotificationButton;
    private final JTextField currentTemperatureDisplay;
    private final JLabel currentTemperatureLabel;
    private final JLabel dailyMaxMinLabel;
    private final JTextField dailyMaxMinTempsDisplay;
    private final JTextField futureDataDisplay;
    private final JLabel futureDataDisplayLabel;
    private final JLabel precipitationPercentageLabel;
    private final JTextField precipitationPercentageDisplay;
    private final JButton searchButton;
    private final JButton settingsButton;
    private final JButton weatherIconDisplay;
    private final JButton logoutButton;
    private final JLabel bufferField1;
    private final JLabel bufferField2;

    private final LoggedInViewModel loggedInViewModel;
    private final NotificationController notificationController;
    private final SettingsButtonController settingsButtonController;
    private final LocationLookupController locationLookupController;
    private final WeatherLookupController weatherLookupController;
    private final LoginSignupSwitchController loginSignupSwitchController;
  
    // End of variable declaration

    // TODO: ADD ALL USE CASE CONTROLLERS TO INITIALIZER "LoginController controller, "

    public LoggedInView(LoggedInViewModel loggedInViewModel, NotificationController notificationController, LocationLookupController locationLookupController, 
                        WeatherLookupController weatherLookupController, LoginSignupSwitchController loginSignupSwitchController, SettingsButtonController settingsButtonController) {
        /**
         * TODO: Implement basic controler classes for the view functionality
         */

        this.settingsButtonController = settingsButtonController;
        this.notificationController = notificationController;
        this.loggedInViewModel = loggedInViewModel;
        this.locationLookupController = locationLookupController;
        this.weatherLookupController = weatherLookupController;
        this.loginSignupSwitchController = loginSignupSwitchController;
        loggedInViewModel.addPropertyChangedListener(this);

        // Object creation for visual elements

        // JLabels - titles and labels
        applicationTitle = new JLabel(loggedInViewModel.APPLICATION_TITLE);
        currentTemperatureLabel = new JLabel(loggedInViewModel.CURRENT_TEMPERATURE_LABEL);
        dailyMaxMinLabel = new JLabel(loggedInViewModel.DAILY_MAX_MIN_LABEL);
        futureDataDisplayLabel = new JLabel(loggedInViewModel.FUTURE_DATA_LABEL);
        precipitationPercentageLabel = new JLabel(loggedInViewModel.PRECIPITATION_CHANCE_LABEL);
        bufferField1 = new JLabel(loggedInViewModel.BUFFER_EMPTY_TEXT);
        bufferField2 = new JLabel(loggedInViewModel.BUFFER_EMPTY_TEXT);

        // JTextFields - return areas for weather data and search intake area
        // Input field
        citySearchInputField = new JTextField(loggedInViewModel.CITY_SEARCH_DEFAULT_TEXT);
        //Output fields
        currentTemperatureDisplay = new JTextField(loggedInViewModel.CURRENT_TEMPERATURE_DEFAULT_TEXT);
        dailyMaxMinTempsDisplay = new JTextField(loggedInViewModel.DAILY_MAX_MIN_DEFAULT_TEXT);
        futureDataDisplay = new JTextField(loggedInViewModel.FUTURE_DATA_DEFAULT_TEXT);
        precipitationPercentageDisplay = new JTextField(loggedInViewModel.PRECIPITATION_CHANCE_DEFAULT_TEXT);

        // JButtons - interaction points for the user and icon display area
        // Input button - requires listeners
        createNotificationButton = new JButton(loggedInViewModel.CREATE_NOTIFICATION_BUTTON_LABEL);
        searchButton = new JButton(loggedInViewModel.CITY_SEARCH_BUTTON_LABEL);
        settingsButton = new JButton(loggedInViewModel.SETTING_BUTTON_IMG);
        logoutButton = new JButton(loggedInViewModel.LOGOUT_BUTTON_TEXT);
        // Output button - no functionality on click
        weatherIconDisplay = new JButton(loggedInViewModel.DEFAULT_DISPLAY_IMG);
        weatherIconDisplay.setBackground(Color.DARK_GRAY);

        // End of object creation for visual elements

        // Makes the title label text size larger
        applicationTitle.setFont(loggedInViewModel.TITLE_FONT);

        // This section is for adding all the listeners to the view buttons, adding key listeners to input fields etc.

        // Button action listeners
        createNotificationButton.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createNotificationButton)) {
                            notificationController.execute(loggedInViewModel.getWindowState().getUsername());
                        }
                    }
                }
        );

        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            locationLookupController.execute(loggedInViewModel.getWindowState().getCurrentCityName());
                        }
                    }
                }
        );

        settingsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(settingsButton)) {
                            // TODO: ADD CONTROLLER EXECUTE LINE HERE
                           settingsButtonController.execute();
                        }
                    }
                }
        );

        logoutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logoutButton)) {
                            loginSignupSwitchController.execute(true);
                        }
                    }
                }
        );

        // Key listener
        citySearchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoggedInState currentState = loggedInViewModel.getWindowState();
                        currentState.setCurrentCityName(citySearchInputField.getText() + e.getKeyChar());
                        loggedInViewModel.setWindowState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        // End of listener addition and creation

        // Sets view layout to the GridLayout format from java.awt (6 rows and 3 columns)
        this.setLayout(new GridLayout(6, 3));

        // This section adds elements to the view in the specified order.
        // Code is further broken down by "LINE" for to help with readability of location for each component

        // Line 1 (Page Start)
        this.add(applicationTitle);
        this.add(bufferField1);
        this.add(bufferField2);

        // Line 2
        this.add(searchButton);
        this.add(createNotificationButton);
        this.add(settingsButton);

        // Line 3
        this.add(citySearchInputField);
        this.add(currentTemperatureLabel);
        this.add(precipitationPercentageLabel);

        // Line 4
        this.add(weatherIconDisplay);
        this.add(currentTemperatureDisplay);
        this.add(precipitationPercentageDisplay);

        // Line 5
        this.add(dailyMaxMinLabel);
        this.add(futureDataDisplayLabel);
        this.add(logoutButton);

        // Line 6
        this.add(dailyMaxMinTempsDisplay);
        this.add(futureDataDisplay);

        // End of adding components to JPanel

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();

        Location currentLocation = state.getCurrentRetrievedLocation();
        if (currentLocation != null) {
            String fieldText = currentLocation.getName();

            if (!currentLocation.getRegion().isEmpty()) {
                fieldText += ", " + currentLocation.getRegion();
            }

            if (!currentLocation.getCountry().isEmpty()) {
                fieldText += ", " + currentLocation.getCountry();
            }

            citySearchInputField.setText(fieldText);

            if(!state.getLocationWeatherDataLatLon().equals(currentLocation.getLat() + "," + currentLocation.getLon()))
            {
                loggedInViewModel.getWindowState().setLocationWeatherDataLatLon(currentLocation.getLat() + "," + currentLocation.getLon());
                weatherLookupController.execute(currentLocation);
            }
        } else {
            citySearchInputField.setText("Location not found!");
            state.setLocationWeatherData(null);
        }

        LocationWeatherData currentWeather = state.getLocationWeatherData();
        if(state.getLocationWeatherData() != null) {
            currentTemperatureDisplay.setText(Float.toString(currentWeather.getCurrentTempC()) + "°C");
            precipitationPercentageDisplay.setText(String.valueOf(currentWeather.getDailyChanceOfPrecipitation() + "%"));
            dailyMaxMinTempsDisplay.setText("("+ currentWeather.getMinDailyTemp() + "°C : " + currentWeather.getMaxDailyTemp() +"°C)");

            String futureDataText = "";
            for(LocationWeatherForecastData forecast : currentWeather.getWeatherForecast()) {
                if(forecast == null)
                    continue;

                String forecastText = "(Day ";
                forecastText += ChronoUnit.DAYS.between(LocalDate.now(), forecast.getForecastDate());
                forecastText += ": " + forecast.getForecastMinTempC() + "°C";
                forecastText += " : " + forecast.getForecastMaxTempC() + "°C";
                forecastText += ", " + forecast.getForecastChanceOfPrecipitation() + "% Precipitation)";
                futureDataText += forecastText + " | ";
            }

            if(futureDataText.length() > 0) {
                futureDataText = futureDataText.substring(0, futureDataText.length() - 2);
            }

            futureDataDisplay.setText(futureDataText);

            try {
                ImageIcon weatherIcon = new ImageIcon(new URL("https:" + currentWeather.getCurrentConditionIconURL()));
                Image convertImg = weatherIcon.getImage();
                Image newConvertImage = convertImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                weatherIconDisplay.setIcon(new ImageIcon(newConvertImage));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}