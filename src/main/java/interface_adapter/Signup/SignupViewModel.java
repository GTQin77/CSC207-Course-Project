package interface_adapter.Signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * SignupViewModel for the Signup use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupViewModel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class SignupViewModel extends ViewModel {

    public final String TITLE_LABEL = "Sign Up View";
    public final String USERNAME_LABEL = "Enter Username";
    public final String PASSWORD_LABEL = "Enter Password";
    public final String REPEAT_PASSWORD_LABEL = "Repeat password";
    public final String LOCATION_LABEL = "Latitude, longitude (Optional)";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event to notify observers that the state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel. Listeners are notified of changes to the ViewModel's properties.
     *
     * @param listener The PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }
}
