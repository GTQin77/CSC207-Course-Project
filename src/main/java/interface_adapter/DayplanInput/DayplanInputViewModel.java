package interface_adapter.DayplanInput;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class DayplanInputViewModel extends ViewModel {


    public DayplanInputViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
