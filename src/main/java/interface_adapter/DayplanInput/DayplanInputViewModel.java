package interface_adapter.DayplanInput;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DayplanInputViewModel extends ViewModel {
    public final String TITLE_LABEL = "Dayplan Input";
    public final String CITY_LABEL = "Input City and Province";
    public final String MEALS_LABEL = "Number of Meals";
    public final String ACTIVITIES_LABEL = "Number of Activities";
    public final String DESCRIPTION_LABEL = "Describe Your Day";

    public final String OK_BUTTON_LABEL = "OK";

    private DayplanInputState state = new DayplanInputState();

    public DayplanInputViewModel() {super("DayplanInput");}

    public void setState(DayplanInputState state) { this.state = state;     }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DayplanInputState getState() { return state; }
}
