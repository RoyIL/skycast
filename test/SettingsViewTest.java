import app.Main;
import data_access.FileUserDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import view.LabelTextPannel;
import view.LoggedInView;
import view.SettingsView;
import view.SignupView;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class SettingsViewTest {

    private FileUserDataAccessObject fudao;

    @Before
    public void refreshDb() {
        try {
            fudao = new FileUserDataAccessObject("./users.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JButton getSignUpButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView sv = (SignupView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(4);

        return (JButton) buttons.getComponent(0); // this should be the signup button
    }

    public JButton getSettingsButton() {

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoggedInView loggedInView = (LoggedInView) jp2.getComponent(2);
        return (JButton) loggedInView.getComponent(5);
    }

    public JLabel getTitleBox() {

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp1 = (JPanel) cp;
        JPanel jp2 = (JPanel) jp1.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);

        return (JLabel) settingsView.getComponent(0); // this should be the title box.
    }

    public LabelTextPannel getPasswordInputField() {

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp1 = (JPanel) cp;
        JPanel jp2 = (JPanel) jp1.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);

        return (LabelTextPannel) settingsView.getComponent(1); // this should be the password input field.
    }

    public LabelTextPannel getPhoneNumberInputField() {

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp1 = (JPanel) cp;
        JPanel jp2 = (JPanel) jp1.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);

        return (LabelTextPannel) settingsView.getComponent(2); // this should be the phone number input field.
    }

    public JButton getBackButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp1 = (JPanel) cp;
        JPanel jp2 = (JPanel) jp1.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);
        JPanel buttons = (JPanel) settingsView.getComponent(3);

        return (JButton) buttons.getComponent(0); // this should be the back button.
    }

    public JButton getEnterButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp1 = (JPanel) cp;
        JPanel jp2 = (JPanel) jp1.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);
        JPanel buttons = (JPanel) settingsView.getComponent(3);

        return (JButton) buttons.getComponent(1); // this should be the enter button.
    }

    public void fillInSignupFields(String username, String password, String repeatPassword) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        SignupView sv = (SignupView) jp2.getComponent(0);

        ((JTextField)((LabelTextPannel)sv.getComponent(1)).getComponent(1)).setText(username);
        ((JTextField)((LabelTextPannel)sv.getComponent(2)).getComponent(1)).setText(password);
        ((JTextField)((LabelTextPannel)sv.getComponent(3)).getComponent(1)).setText(repeatPassword);
    }

    public void fillInSettingsFields(String password, String phoneNumber) {

        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);

        SettingsView settingsView = (SettingsView) jp2.getComponent(3);

        ((JTextField)((LabelTextPannel)settingsView.getComponent(1)).getComponent(1)).setText(password);
        ((JTextField)((LabelTextPannel)settingsView.getComponent(2)).getComponent(1)).setText(phoneNumber);
    }

    @org.junit.Test
    public void testSettingsButtonPresent() {
        Main.main(null);
        JButton settingsButton = getSettingsButton();
        assert(settingsButton.getText().equals("Settings"));
    }
    @org.junit.Test
    public void testTitlePresent() {
        Main.main(null);
        JLabel title = getTitleBox();
        assert(title.getText().equals("Settings"));
    }

    @org.junit.Test
    public void testInputFieldsPresent() {
        Main.main(null);
        LabelTextPannel passwordPanel = getPasswordInputField();
        LabelTextPannel phoneNumberPanel = getPhoneNumberInputField();
        assert(passwordPanel.getLabel().equals("Change Password")
        && phoneNumberPanel.getLabel().equals("Change Phone Number"));
    }

    @org.junit.Test
    public void testButtonsPresent() {
        Main.main(null);
        JButton enterButton = getEnterButton();
        JButton backButton = getBackButton();
        assert(enterButton.getText().equals("Enter") && backButton.getText().equals("Back"));
    }

    @org.junit.Test
    public void testSettingsSet() {
        Main.main(null);

        JButton signUpButton = getSignUpButton();

        String username = "testuser_";
        String phoneNumber = "6472982945";

        while(fudao.exists(username)) {
            username += "1";
        }

        fillInSignupFields(username, "testPassword", "testPassword");

        signUpButton.doClick();

        JButton settingsButton = getSettingsButton();
        settingsButton.doClick();

        fillInSettingsFields("testPassword2", phoneNumber);

        JButton enterButton = getEnterButton();
        enterButton.doClick();

        refreshDb();

        User user = fudao.get(username);

        assert (user.getPassword().equals("testPassword2") && user.getPhoneNumber().equals(phoneNumber));

    }

    @org.junit.Test
    public void SetSettingsPhoneNumberBlank (){
        Main.main(null);

        JButton signUpButton = getSignUpButton();

        String username = "testuser_";
        String phoneNumber = "6472982945";

        while(fudao.exists(username)) {
            username += "1";
        }

        fillInSignupFields(username, "testPassword", "testPassword");

        signUpButton.doClick();

        JButton settingsButton = getSettingsButton();
        settingsButton.doClick();

        fillInSettingsFields("testPassword", phoneNumber);

        JButton enterButton = getEnterButton();
        enterButton.doClick();

        fillInSettingsFields("testPassword2", "   ");
        enterButton.doClick();

        refreshDb();

        User user = fudao.get(username);
        assert (user.getPassword().equals("testPassword2") && user.getPhoneNumber().equals(phoneNumber));
    }

    @org.junit.Test
    public void SetSettingsPasswordBlank () {
        Main.main(null);

        JButton signUpButton = getSignUpButton();

        String username = "testuser_";
        String phoneNumber = "6472982945";
        String newPhoneNumber = "6472982946";

        while(fudao.exists(username)) {
            username += "1";
        }

        fillInSignupFields(username, "testPassword", "testPassword");

        signUpButton.doClick();

        JButton settingsButton = getSettingsButton();
        settingsButton.doClick();

        fillInSettingsFields("testPassword", phoneNumber);

        JButton enterButton = getEnterButton();
        enterButton.doClick();

        fillInSettingsFields("  ", newPhoneNumber);
        enterButton.doClick();

        refreshDb();

        User user = fudao.get(username);
        assert (user.getPassword().equals("testPassword") && user.getPhoneNumber().equals(newPhoneNumber));
    }

    @org.junit.Test
    public void SetSettingsAllBlank () {
        Main.main(null);

        JButton signUpButton = getSignUpButton();

        String username = "testuser_";
        String phoneNumber = "6472982945";

        while(fudao.exists(username)) {
            username += "1";
        }

        fillInSignupFields(username, "testPassword", "testPassword");

        signUpButton.doClick();

        JButton settingsButton = getSettingsButton();
        settingsButton.doClick();

        fillInSettingsFields("", phoneNumber);

        JButton enterButton = getEnterButton();
        enterButton.doClick();

        fillInSettingsFields("  ", "  ");
        enterButton.doClick();

        refreshDb();

        User user = fudao.get(username);
        assert (user.getPassword().equals("testPassword") && user.getPhoneNumber().equals(phoneNumber));
    }
}


