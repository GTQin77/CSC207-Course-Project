package services;

import data_access.DayPlanDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import entity.Business;
import entity.Dayplan;
import entity.PreviousPlan;
import entity.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private User currentUser;
    private ArrayList<String> currentLocation;
    private Dayplan dayplan;
    private PropertyChangeSupport support;
    private PreviousPlan previousPlan;
    private UserSignupDataAccessInterface userSignupDAO;
    private DayPlanDataAccessInterface dayplanDAO;


    public UserService(UserSignupDataAccessInterface userSignupDAO, DayPlanDataAccessInterface dayplanDAO) {
        this.userSignupDAO = userSignupDAO;
        this.dayplanDAO = dayplanDAO;
        this.dayplan = loadInitialDayplan();
        this.currentUser = loadInitialUser();
        support = new PropertyChangeSupport(this);
    }

    private User loadInitialUser() {
        ArrayList<Double> mockLocation = new ArrayList<>();
        mockLocation.add(43.6598);
        mockLocation.add(79.3973);
        User user = new User("naleraoei4ujrnakahfejf87340", "123", mockLocation);
        Dayplan dayplan1 = loadInitialDayplan();
        ArrayList<Dayplan> dayplans = new ArrayList<>();
        dayplans.add(dayplan1);
        user.setDayPlans(dayplans);
        if (!userSignupDAO.userExists("naleraoei4ujrnakahfejf87340")) {
            userSignupDAO.saveUser(user);
        }

        return user;
    }

    private Dayplan loadInitialDayplan() {
        dayplan = new Dayplan();
        ArrayList<Double> location = new ArrayList<>();
        location.add(22.3344);
        location.add(-22.3344);
        User user = new User("naleraoei4ujrnakahfejf87340", "123",location);

        ArrayList<Business> plan = new ArrayList<>();

        plan.add(new Business("mockOne", location, 11.0, "223-334-5566", "$", 3.9f, "meal"));
        plan.add(new Business("mockTwo", location, 13.0, "443-334-5566", "$$$", 4.7f, "activity"));

        ArrayList<String> businessIDs = new ArrayList<>();
        businessIDs.add("123");
        businessIDs.add("456");

        dayplan.setBusinessIDs(businessIDs);
        dayplan.setUser(user);
        dayplan.setPlan(plan);
        dayplan.setLocation(location);
        dayplan.setDescription("A happy day");
        dayplan.setnumActivities(1);
        dayplan.setNumMeals(1);
        dayplan.setVibe("Active, Inspiring, Motivational");
        dayplan.setCity("Toronto");
        dayplanDAO.saveDayPlan(dayplan);
        return dayplan;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    private Business business;

    public String getPrevView() {
        return prevView;
    }

    public void setPrevView(String prevView) {
        this.prevView = prevView;
    }

    private String prevView;

    public Dayplan getDayplan() {
        return dayplan;
    }

    public void setDayplan(Dayplan newDayplan) {
        Dayplan oldDayplan = this.dayplan;
        this.dayplan = newDayplan;
        support.firePropertyChange("dayplan", oldDayplan, newDayplan);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public ArrayList<String> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String locationString) {
        this.currentLocation = new ArrayList<>(List.of(locationString.split(",")));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public PreviousPlan getPreviousPlan() {
        return previousPlan;
    }

    public void setPreviousPlan(PreviousPlan previousPlan) {
        this.previousPlan = previousPlan;
    }



}
