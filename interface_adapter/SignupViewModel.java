package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    /**
     * NOTICE: ALL STRING NAMES ARE TEMPORARY AND ONLY SERVE FOR TESTING FUNCTIONALITY OF
     * OUR FIRST USE CASE. THESE WILL BE UPDATED AS THE PROJECT PROGRESSES
     */
    public final String TITLE_BOX = "Signup View";
    public final String SIGNUP_USERNAME_LABEL = "Enter username here...";
    public final String SIGNUP_PASSWORD_LABEL = "Enter password here...";
    public final String SIGNUP_REP_PASSWORD_LABEL = "Repeat password here...";

    public final String SIGNUP_BUTTON_LABEL = "Signup";
    public final String CANCEL_SIGNUP_BUTTON_LABEL = "Cancel Signup";

    private SignupState windowState = new SignupState();

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public SignupViewModel() {
        super("sign up");
    }

    public void setWindowState(SignupState newWindowState) {
        this.windowState = newWindowState;
    }

    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);
    }

    public void addPropertyChangedListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }

    public SignupState getWindowState() {
        return windowState;
    }

}
