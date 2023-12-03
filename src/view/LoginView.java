package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login";

    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);

    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final LoginController loginController;

    private final JButton login;

    private final JButton cancel;
    public LoginView(LoginController controller, LoginViewModel loginViewModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        loginViewModel.addPropertyChangedListener(this);

        JLabel title = new JLabel(loginViewModel.TITLE_BOX);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPannel usernameInfo = new LabelTextPannel(
                new JLabel(loginViewModel.LOGIN_USERNAME_LABEL), usernameInputField);
        LabelTextPannel passwordInfo = new LabelTextPannel(
                new JLabel(loginViewModel.LOGIN_PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();
        login = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);
        cancel = new JButton(loginViewModel.CANCEL_LOGIN_BUTTON_LABEL);
        buttons.add(cancel);

        login.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            loginController.execute(usernameInputField.getText(),
                                    String.valueOf(passwordInputField.getPassword()));
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
                        LoginState currentState = loginViewModel.getWindowState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        loginViewModel.setWindowState(currentState);
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
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
