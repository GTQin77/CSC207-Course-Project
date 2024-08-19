package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * SignupViewManagerModel for the Signup use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupViewManagerModel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class ViewManagerModel {

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

    /**
     * Fires a property change event to notify observers that the state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel. Listeners are notified of changes to the ViewModel's properties.
     *
     * @param listener The PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}