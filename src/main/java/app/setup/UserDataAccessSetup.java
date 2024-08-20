package app.setup;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import data_access.UserSignupDataAccessObject;
import interface_adapter.ViewManagerModel;
import services.RefreshService;
import services.UserService;
import view.ViewManager;

import javax.swing.*;

public class UserDataAccessSetup {

    /**
     * Creates and returns an instance of {@link UserSignupDataAccessObject} for accessing user data.
     */
    public UserSignupDataAccessInterface createUserSignupDataAccess() {
        UserSignupDataAccessObject userDAO = new UserSignupDataAccessObject();
        userDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        return userDAO;
    }

    /**
     * Creates and returns an instance of {@link DayPlanDataAccessObject} for accessing dayplan data.
     */
    public DayPlanDataAccessInterface createDayPlanDataAccess() {
        DayPlanDataAccessObject dayplanDAO = new DayPlanDataAccessObject();
        dayplanDAO.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
        return dayplanDAO;
    }

}
