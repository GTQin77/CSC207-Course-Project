package app.setup;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import data_access.UserSignupDataAccessObject;

public class UserDataAccessSetup {
    public UserSignupDataAccessInterface createUserSignupDataAccess() {
        UserSignupDataAccessObject userDAO = new UserSignupDataAccessObject();
        userDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        return userDAO;
    }

    public DayPlanDataAccessInterface createDayPlanDataAccess() {
        DayPlanDataAccessObject dayplanDAO = new DayPlanDataAccessObject();
        dayplanDAO.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
        return dayplanDAO;
    }
}
