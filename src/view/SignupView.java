package view;

import interface_adapter.login_signup_switch.LoginSignupSwitchController;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements PropertyChangeListener, ActionListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final LoginSignupSwitchController loginSignupSwitchController;

    private final JButton signUp;
    private final JButton login;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, LoginSignupSwitchController loginSignupSwitchController) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.loginSignupSwitchController = loginSignupSwitchController;
        signupViewModel.addPropertyChangedListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_BOX);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPannel usernameInfo = new LabelTextPannel(
                new JLabel(signupViewModel.SIGNUP_USERNAME_LABEL), usernameInputField);
        LabelTextPannel passwordInfo = new LabelTextPannel(
                new JLabel(signupViewModel.SIGNUP_PASSWORD_LABEL), passwordInputField);
        LabelTextPannel repeatPasswordInfo = new LabelTextPannel(
                new JLabel(signupViewModel.SIGNUP_REP_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        login = new JButton(signupViewModel.LOGIN_FROM_SIGNUP_BUTTON_LABEL);
        buttons.add(login);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            signupController.execute(usernameInputField.getText(),
                                    String.valueOf(passwordInputField.getPassword()),
                                    String.valueOf(repeatPasswordInputField.getPassword()));
                        }
                    }
                }
        );
        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                           loginSignupSwitchController.execute(true);
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getWindowState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setWindowState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}