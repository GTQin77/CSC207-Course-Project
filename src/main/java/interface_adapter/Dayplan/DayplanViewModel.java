package interface_adapter.Dayplan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;
import entity.User;
import interface_adapter.ViewModel;

public class DayplanViewModel extends ViewModel{
    public final String TITLE_LABEL = "Dayplan View";

    public DayplanViewModel() {
        super("Dayplan");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        ;;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
