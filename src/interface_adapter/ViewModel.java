package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private String viewName;

    public ViewModel(String newViewName){
        this.viewName = newViewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangedListener(PropertyChangeListener myListener);

}
