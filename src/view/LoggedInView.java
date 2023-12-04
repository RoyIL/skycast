package view;

import interface_adapter.loggedin.*;
import interface_adapter.loggedin.settings.SettingsButtonController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.loggedin.notification.NotificationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

    // End of variable declaration

    // TODO: ADD ALL USE CASE CONTROLLERS TO INITIALIZER "LoginController controller, "

    public LoggedInView(LoggedInViewModel loggedInViewModel, NotificationController notificationController,
                        SettingsButtonController settingsButtonController) {
        /**
         * TODO: Impliment basic controler classes for the view functionality
         */

        this.settingsButtonController = settingsButtonController;
        this.notificationController = notificationController;
        this.loggedInViewModel = loggedInViewModel;
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

        // End of object creation for visual elements

        // Makes the title label text size larger
        applicationTitle.setFont(loggedInViewModel.TITLE_FONT);

        // This section is for adding all the listeners to the view buttons, adding key listeners to input fields etc.

        // Button action listeners
        createNotificationButton.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(createNotificationButton)) {
                        notificationController.execute();
                    }
                }
            }
        );

        searchButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(searchButton)) {
                        // TODO: ADD CONTROLLER EXECUTE LINE HERE
                        int i = 0;
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
                        // TODO: ADD CONTROLLER EXECUTE LINE HERE
                        int i = 0;
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
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

}