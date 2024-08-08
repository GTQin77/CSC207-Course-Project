package interface_adapter.EditInfo;

import interface_adapter.EditInfo.EditInfoState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class EditInfoViewModel extends ViewModel{

    public final String TITLE_LABEL = "Edit Info View";
    public final String USERNAME_LABEL = "Username";
    public final String PASSWORD_LABEL = "Password(Optional)";
    public final String REPEAT_PASSWORD_LABEL = "Repeat password(Optional unless changing password)";
    public final String LOCATION_LABEL = "Location (Optional)";

    public final String SUBMIT_CHANGES_BUTTON_LABEL = "Submit Changes";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private EditInfoState state = new EditInfoState();

    public EditInfoViewModel() {
        super("EditUserInfo");
    }

    public void setState(EditInfoState newState) {
        EditInfoState oldState = this.state;
        if (!oldState.equals(newState)) {
            this.state = newState;
            support.firePropertyChange("state", oldState, newState);
        }
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditInfoState getState() {
        return state;
    }

}
