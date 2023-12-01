package view;

import interface_adapter.loggedin.*;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    // Variables declaration - do not modify
    private JLabel applicationTitle;

    private JTextField citySearchInputField;
    private JButton createNotificationButton;
    private JTextField currentTemperatureDisplay;
    private JLabel currentTemperatureLabel;
    private JLabel dailyMaxMinLabel;
    private JTextField dailyMaxMinTempsDisplay;
    private JTextField futureDataDisplay;
    private JLabel futureDataDisplayLabel;
    private JLabel precipitationPercentageLabel;
    private JTextField precipitationPercentageDisplay;
    private JButton searchButton;
    private JButton settingsButton;
    private JButton weatherIconDisplay;
    private JButton logoutButton;

    private final LoggedInViewModel loggedInViewModel;

    public LoggedInView(LoginController controller, LoggedInViewModel loggedInViewModel) {
        /**
         * TODO: Impliment basic controler classes for the view functionality
         */
        // this.logiController = controller;
        this.loggedInViewModel = loggedInViewModel;
        loggedInViewModel.addPropertyChangedListener(this);

        // Object creation for visual elements
        applicationTitle = new JLabel(loggedInViewModel.APPLICATION_TITLE);
        currentTemperatureLabel = new JLabel(loggedInViewModel.CURRENT_TEMPERATURE_LABEL);
        dailyMaxMinLabel = new JLabel(loggedInViewModel.DAILY_MAX_MIN_LABEL);
        futureDataDisplayLabel = new JLabel(loggedInViewModel.FUTURE_DATA_LABEL);
        precipitationPercentageLabel = new JLabel(loggedInViewModel.PRECIPITATION_CHANCE_LABEL);

        citySearchInputField = new JTextField(loggedInViewModel.CITY_SEARCH_DEFAULT_TEXT);
        currentTemperatureDisplay = new JTextField(loggedInViewModel.CURRENT_TEMPERATURE_DEFAULT_TEXT);
        dailyMaxMinTempsDisplay = new JTextField(loggedInViewModel.DAILY_MAX_MIN_DEFAULT_TEXT);
        futureDataDisplay = new JTextField(loggedInViewModel.FUTURE_DATA_DEFAULT_TEXT);
        precipitationPercentageDisplay = new JTextField(loggedInViewModel.PRECIPITATION_CHANCE_DEFAULT_TEXT);

        createNotificationButton = new JButton(loggedInViewModel.CREATE_NOTIFICATION_BUTTON_LABEL);
        searchButton = new JButton(loggedInViewModel.CITY_SEARCH_BUTTON_LABEL);
        settingsButton = new JButton(loggedInViewModel.SETTING_BUTTON_IMG);
        weatherIconDisplay = new JButton(loggedInViewModel.DEFAULT_DISPLAY_IMG);
        logoutButton = new JButton(loggedInViewModel.LOGOUT_BUTTON_TEXT);

        this.setLayout(new BorderLayout());

        // This section adds elements to the view in the specified location.
        // Code is further broken down by "LINE" for BorderLayout

        // Line 1 (Page Start)
        this.add(applicationTitle, BorderLayout.PAGE_START);

        // Line 2
        this.add(searchButton, BorderLayout.LINE_START);
        this.add(createNotificationButton, BorderLayout.CENTER);
        this.add(settingsButton, BorderLayout.LINE_END);

        // Line 3
        this.add(citySearchInputField, BorderLayout.LINE_START);
        this.add(currentTemperatureLabel, BorderLayout.CENTER);
        this.add(precipitationPercentageLabel, BorderLayout.LINE_END);

        // Line 4
        this.add(weatherIconDisplay, BorderLayout.LINE_START);
        this.add(currentTemperatureDisplay, BorderLayout.CENTER);
        this.add(precipitationPercentageDisplay, BorderLayout.LINE_END);

        // Line 5
        this.add(dailyMaxMinLabel, BorderLayout.LINE_START);
        this.add(futureDataDisplayLabel, BorderLayout.LINE_END);

        // Line 6
        this.add(dailyMaxMinTempsDisplay, BorderLayout.LINE_START);
        this.add(futureDataDisplay, BorderLayout.LINE_END);

        // Line 7
        this.add(logoutButton, BorderLayout.PAGE_END);

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