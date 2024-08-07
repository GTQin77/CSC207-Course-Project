package interface_adapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * View model of the program.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for ViewModel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/ViewModel.java">github.com</a>.
 * </p>
 */
public abstract class ViewModel {

//    public enum ViewState {
//        WELCOME,
//        SIGNING_UP,
//        LOGGING_IN,
//        LOGGED_IN,
//    }
//    private ViewState state = ViewState.WELCOME;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

//    public void setState(ViewState state) {
//        ViewState oldState = this.state;
//        this.state = state;
//        support.firePropertyChange("state", oldState, this.state);
//    }


    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}