package view;

import interface_adapter.loggedin.settings.SettingsViewModel;
import interface_adapter.loggedin.settings.SettingsState;
import interface_adapter.loggedin.settings.SettingsController;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "settings";
    private final JButton back;
    private final JButton enter;
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JTextField phoneNumberInputField = new JTextField(10);
    private final JTextField regionInputField = new JTextField();
    //private final HashMap<String,String> settingsMap;
    private final SettingsViewModel settingsViewModel;
    private final SettingsController settingsController;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private String newPassword;
    private String newPhoneNumber;
    private String newRegion;

    public SettingsView(SettingsViewModel viewModel, SettingsController controller,
                        ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {

        this.settingsViewModel = viewModel;
        this.settingsController = controller;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

        newPassword = "";
        newPhoneNumber = "";
        newRegion = "";

        settingsViewModel.addPropertyChangedListener(this);

        JLabel title = new JLabel(settingsViewModel.TITLE_BOX);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        back = new JButton(settingsViewModel.BACK_BUTTON);
        enter = new JButton(settingsViewModel.ENTER);
        LabelTextPannel passwordInfo = new LabelTextPannel(
                new JLabel(settingsViewModel.PASSWORD_INPUT_FIELD), passwordInputField);
        LabelTextPannel phoneNumberInfo = new LabelTextPannel(
                new JLabel(settingsViewModel.PHONE_NUMBER_INPUT_FIELD), phoneNumberInputField);


        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        enter.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(enter)) {
                            settingsController.execute(newPassword, newPhoneNumber);
                        }
                    }
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        newPassword += e.getKeyChar();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        phoneNumberInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        newPhoneNumber += e.getKeyChar();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new GridLayout(5, 0));

        this.add(title);
        this.add(passwordInfo);
        this.add(phoneNumberInfo);
        this.add(back);
        this.add(enter);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsState state = (SettingsState) evt.getNewValue();
        if (state.getPhoneNumberError() != null) {
            JOptionPane.showMessageDialog(this, state.getPhoneNumberError());
        }
    }
}
