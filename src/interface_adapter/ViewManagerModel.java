package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    // Instance attribute for keeping track of which view is active
    private String activeViewName = "";

    // Facilitates the creation of the property change event
    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    // Returns the current active view
    public String getActiveViewName() {
        return activeViewName;
    }

    // Sets a new view to be the active view
    public void setActiveView(String newActiveView) {
        this.activeViewName = newActiveView;
    }

    // Says that the active view has changed
    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("view", null, this.activeViewName);
    }

    // Facilitates the creation of the property change event
    public void addPropertyChangeListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }
}
