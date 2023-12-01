package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName = "";

    private final PropertyChangeSupport checkerHelp = new PropertyChangeSupport(this);

    public String getActiveViewName() {
        return activeViewName;
    }
    public void setActiveView(String newActiveView) {
        this.activeViewName = newActiveView;
    }

    // activeViewName
    public void firePropertyChanged() {
        checkerHelp.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener newListener) {
        checkerHelp.addPropertyChangeListener(newListener);
    }
}
