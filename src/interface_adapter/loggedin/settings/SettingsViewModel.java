package interface_adapter.loggedin.settings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel extends ViewModel{

    public static final String TITLE_BOX = "Settings";
    public static final String PASSWORD_INPUT_FIELD = "Change Password";
    public static final String PHONE_NUMBER_INPUT_FIELD = "Change Phone Number";
    public static final String REGION_INPUT_FIELD = "Change Region ";
    public static final String ENTER = "Enter";
    public static final String BACK_BUTTON = "Back";

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    private SettingsState windowState = new SettingsState();
    public SettingsViewModel() {super("settings");
    }

    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("windowState", null, this.windowState);}

    public void addPropertyChangedListener(PropertyChangeListener myListener) {
        checkerHelp.addPropertyChangeListener(myListener);
    }

    public void setWindowState(SettingsState newWindowState) {this.windowState = newWindowState;
    }

    public SettingsState getWindowState() {
        return this.windowState;
    }

}
