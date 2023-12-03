import app.Main;
import data_access.FileUserDataAccessObject;
import org.junit.Before;
import view.LabelTextPannel;
import view.SignupView;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class SignupViewTest {
    private FileUserDataAccessObject fudao;

    @Before
    public void refreshDb() {
        try {
            fudao = new FileUserDataAccessObject("./users.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JButton getButton() {
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

        return (JButton) buttons.getComponent(0); // this should be the signup
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

    /**
     *
     * Test that the signup button is present and where it is expected to be
     */
    @org.junit.Test
    public void testSignupButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Signup"));
    }

    @org.junit.Test
    public void testSignupUserCreated() {
        Main.main(null);
        JButton button = getButton();

        String username = "testuser_";

        while(fudao.exists(username)) {
            username += "1";
        }

        fillInSignupFields(username, "testPassword", "testPassword");
        button.doClick();

        refreshDb();
        assert(fudao.exists(username));
    }

}
