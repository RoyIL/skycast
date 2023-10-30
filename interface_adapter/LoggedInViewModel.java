package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    /**
     * NOTICE: ALL STRING NAMES ARE TEMPORARY AND ONLY SERVE FOR TESTING FUNCTIONALITY OF
     * OUR FIRST USE CASE. THESE WILL BE UPDATED AS THE PROJECT PROGRESSES
     */
    public final String TITLE_BOX = "Logged In View";

    private SignupState windowState = new SignupState();

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public LoggedInViewModel() {
        super("logged in");
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
