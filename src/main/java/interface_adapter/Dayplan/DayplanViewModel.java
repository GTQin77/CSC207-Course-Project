package interface_adapter.Dayplan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;

import entity.Business;
import entity.User;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DayplanViewModel extends ViewModel {
    private ArrayList<Business> businesses;
    private PropertyChangeSupport support;


    public DayplanViewModel() {
        super("DayplanView");
        this.support = new PropertyChangeSupport(this);
        this.businesses = new ArrayList<>();
    }

    public ArrayList<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        ArrayList<Business> oldBusinesses = this.businesses;
        this.businesses = businesses;
        support.firePropertyChange("businesses", oldBusinesses, businesses);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("update", null, this);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}