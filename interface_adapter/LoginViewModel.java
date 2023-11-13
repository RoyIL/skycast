package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    /**
     * NOTICE: ALL STRING NAMES ARE TEMPORARY AND ONLY SERVE FOR TESTING FUNCTIONALITY OF
     * OUR FIRST USE CASE. THESE WILL BE UPDATED AS THE PROJECT PROGRESSES
     */
    public final String TITLE_BOX = "Log In View";
    public final String LOGIN_USERNAME_LABEL = "Enter username here...";
    public final String LOGIN_PASSWORD_LABEL = "Enter password here...";

    public final String LOGIN_BUTTON_LABEL = "Log In";
    public final String CANCEL_LOGIN_BUTTON_LABEL = "Cancel Login";

    private LoginState windowState = new LoginState();

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public LoginViewModel() {
        super("log in");

    }

    public void setWindowState(LoginState newWindowState) {
        this.windowState = newWindowState;
    }

    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);
    }

    public void addPropertyChangedListener(PropertyChangeListener myListener) {
        checkerHelp.addPropertyChangeListener(myListener);
    }

    public LoginState getWindowState() {
        return windowState;
    }
}
