package interface_adapter.DayplanInput;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * DayplanViewManagerModel for the Signup use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupViewManagerModel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class DayplanInputViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
