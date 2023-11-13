package view;

import interface_adapter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginView {
    public final String viewName = "login";

    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final LoginController loginController;

    private final JButton login;

    private final JButton cancel;
    public SignupView(SignupController controller, SignupViewModel signupViewModel) {

        this.loginController = controller;
        this.loginViewModel = signupViewModel;
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
        cancel = new JButton(signupViewModel.CANCEL_SIGNUP_BUTTON_LABEL);
        buttons.add(cancel);

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
        cancel.addActionListener(this);

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
}
