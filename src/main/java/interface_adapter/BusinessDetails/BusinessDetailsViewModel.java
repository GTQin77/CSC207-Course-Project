package interface_adapter.BusinessDetails;

import entity.Business;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BusinessDetailsViewModel extends ViewModel {
    private String name;
    private String location;
    private String distance;
    private String contactNum;
    private String price;
    private String ratings;
    private String type;
    private PropertyChangeSupport support;

    public BusinessDetailsViewModel() {
        super("BusinessDetails");
        this.support = new PropertyChangeSupport(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Updates the properties of this ViewModel based on a Business object.
     * @param business The business object containing the data to update.
     */
    public void updateBusinessDetails(Business business) {
        setName(business.getName());
        setLocation(String.format("%.2f, %.2f", business.getLocation().get(0), business.getLocation().get(1)));
        setDistance(String.format("%.2f km", business.getDistance()));
        setContactNum(business.getContactNum());
        setPrice(business.getPrice());
        setRatings(String.format("%.1f / 5.0", business.getRatings()));
        setType(business.getType());
        firePropertyChanged();
    }

    /**
     * Fires a property change event indicating that the model has been updated.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("modelUpdated", null, this);
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



