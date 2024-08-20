package interface_adapter.Dayplan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import entity.Business;
import interface_adapter.ViewModel;
import java.util.ArrayList;

public class DayplanViewModel extends ViewModel {
    private ArrayList<Business> businesses;
    private PropertyChangeSupport support;


    public DayplanViewModel() {
        super("DayplanView");
        this.support = new PropertyChangeSupport(this);
        this.businesses = new ArrayList<>();
    }

    /**
     * Returns the current list of businesses.
     *
     * @return the list of businesses currently held by this ViewModel.
     */
    public ArrayList<Business> getBusinesses() {
        return businesses;
    }

    /**
     * Sets the list of businesses and notifies observers about the change.
     *
     * @param businesses the new list of businesses to be set.
     */
    public void setBusinesses(ArrayList<Business> businesses) {
        ArrayList<Business> oldBusinesses = this.businesses;
        this.businesses = businesses;
        support.firePropertyChange("businesses", oldBusinesses, businesses);
    }

    /**
     * Fires a property change event indicating that the model has been updated.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("update", null, this);
    }

    /**
     * Registers a PropertyChangeListener to be notified of changes to this model.
     *
     * @param listener the PropertyChangeListener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}