package view;

import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.notification.NotificationState;
import interface_adapter.loggedin.notification.NotificationViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NotificationView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notif";

    private final NotificationViewModel notificationViewModel;

    private final JLabel toggleDailyMaxMinLabel;
    private final JLabel notificationTitle;
    private final JLabel togglePrecipitationChanceLabel;
    private final JLabel toggleCurrentTemperatureLabel;
    private final JLabel cityNameLabel;
    private final JLabel phoneNumberLabel;
    private final JLabel bufferLabel;

    private final JButton toggleDailyMaxMinButton;
    private final JButton toggleCurrentTemperatureButton;
    private final JButton togglePrecipitationChanceButton;
    private final JButton createNotification;
    private final JButton cancelNotification;

    private final JTextField cityNameInputField;

    public NotificationView(NotificationViewModel notificationViewModel) {
        this.notificationViewModel = notificationViewModel;

        // Creation of local variables
        String toggleDailyMaxMinString;
        String toggleCurrentTemperatureString;
        String togglePrecipitationChanceString;
        String cityForNotificationString;

        NotificationState currentState = this.notificationViewModel.getWindowState();

        if (currentState.getWantCurrentTemperature()) {
            toggleCurrentTemperatureString = notificationViewModel.TOGGLE_BUTTON_TRUE_STATE;
        } else {
            toggleCurrentTemperatureString= notificationViewModel.TOGGLE_BUTTON_FALSE_STATE;
        }

        if (currentState.getWantDailyMaxMin()) {
            toggleDailyMaxMinString = notificationViewModel.TOGGLE_BUTTON_TRUE_STATE;
        } else {
            toggleDailyMaxMinString = notificationViewModel.TOGGLE_BUTTON_FALSE_STATE;
        }

        if (currentState.getWantPrecipitationChance()) {
            togglePrecipitationChanceString = notificationViewModel.TOGGLE_BUTTON_TRUE_STATE;
        } else {
            togglePrecipitationChanceString = notificationViewModel.TOGGLE_BUTTON_FALSE_STATE;
        }

        cityForNotificationString = currentState.getCityName();

        notificationTitle = new JLabel(notificationViewModel.WINDOW_TITLE);
        toggleDailyMaxMinLabel = new JLabel(notificationViewModel.TOGGLE_DAILY_MAX_MIN_LABEL);
        togglePrecipitationChanceLabel = new JLabel(notificationViewModel.TOGGLE_PRECIPITATION_CHANCE_LABEL);
        toggleCurrentTemperatureLabel = new JLabel(notificationViewModel.TOGGLE_CURRENT_TEMPERATURE_LABEL);
        cityNameLabel = new JLabel(notificationViewModel.CITY_NAME_INPUT_LABEL);
        phoneNumberLabel = new JLabel();
        bufferLabel = new JLabel();

        toggleDailyMaxMinButton = new JButton(toggleDailyMaxMinString);
        toggleCurrentTemperatureButton = new JButton(toggleCurrentTemperatureString);
        togglePrecipitationChanceButton = new JButton(togglePrecipitationChanceString);
        createNotification = new JButton(notificationViewModel.CREATE_NOTIFICATION_BUTTON);
        cancelNotification = new JButton(notificationViewModel.CANCEL_NOTIFICATION_BUTTON);

        cityNameInputField = new JTextField(cityForNotificationString);

        cityNameInputField.setText(notificationViewModel.getWindowState().getCityName());

        toggleDailyMaxMinButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toggleDailyMaxMinButton)) {
                            NotificationState newState = notificationViewModel.getWindowState();
                            if (!newState.getWantDailyMaxMin()) {
                                toggleDailyMaxMinButton.setText(notificationViewModel.TOGGLE_BUTTON_TRUE_STATE);
                            } else {
                                toggleDailyMaxMinButton.setText(notificationViewModel.TOGGLE_BUTTON_FALSE_STATE);
                            }
                            newState.setWantDailyMaxMin(!newState.getWantDailyMaxMin());
                        }
                    }
                }
        );

        toggleCurrentTemperatureButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(toggleCurrentTemperatureButton)) {
                            NotificationState newState = notificationViewModel.getWindowState();
                            if (!newState.getWantCurrentTemperature()) {
                                toggleCurrentTemperatureButton.setText(notificationViewModel.TOGGLE_BUTTON_TRUE_STATE);
                            } else {
                                toggleCurrentTemperatureButton.setText(notificationViewModel.TOGGLE_BUTTON_FALSE_STATE);
                            }
                            newState.setWantCurrentTemperature(!newState.getWantCurrentTemperature());
                        }
                    }
                }
        );

        togglePrecipitationChanceButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(togglePrecipitationChanceButton)) {
                        NotificationState newState = notificationViewModel.getWindowState();
                        if (!newState.getWantPrecipitationChance()) {
                            togglePrecipitationChanceButton.setText(notificationViewModel.TOGGLE_BUTTON_TRUE_STATE);
                        } else {
                            togglePrecipitationChanceButton.setText(notificationViewModel.TOGGLE_BUTTON_FALSE_STATE);
                        }
                        newState.setWantPrecipitationChance(!newState.getWantPrecipitationChance());
                    }
                }
            }
        );

        cityNameInputField.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    NotificationState currentState = notificationViewModel.getWindowState();
                    currentState.setCityName(cityNameInputField.getText() + e.getKeyChar());
                    notificationViewModel.setWindowState(currentState);
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            }
        );

        this.setLayout(new GridLayout(6, 2));

        //

        this.add(notificationTitle);
        this.add(bufferLabel);

        this.add(cityNameLabel);
        this.add(cityNameInputField);

        this.add(toggleDailyMaxMinLabel);
        this.add(toggleDailyMaxMinButton);

        this.add(toggleCurrentTemperatureLabel);
        this.add(toggleCurrentTemperatureButton);

        this.add(togglePrecipitationChanceLabel);
        this.add(togglePrecipitationChanceButton);

        this.add(cancelNotification);
        this.add(createNotification);
    }

    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NotificationState state = (NotificationState) evt.getNewValue();
    }

}
