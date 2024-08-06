package interface_adapter.Welcome;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WelcomeViewManagerModel {
    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        String oldView = this.activeViewName;
        this.activeViewName = activeView;
        support.firePropertyChange("view", oldView, activeView);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
