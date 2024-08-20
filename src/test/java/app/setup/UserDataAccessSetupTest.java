package app.setup;

import static org.junit.jupiter.api.Assertions.*;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import data_access.UserSignupDataAccessObject;
import org.junit.jupiter.api.Test;

class UserDataAccessSetupTest {

    @Test
    void createUserSignupDataAccessUserDAO() {
        UserDataAccessSetup dataAccessSetup = new UserDataAccessSetup();
        UserSignupDataAccessInterface userDAO = dataAccessSetup.createUserSignupDataAccess();

        assertNotNull(userDAO);
        assertTrue(userDAO instanceof UserSignupDataAccessObject);

        UserSignupDataAccessObject concreteDAO = (UserSignupDataAccessObject) userDAO;
        assertEquals("./src/main/resources/UserDatabase.csv", concreteDAO.getcsvPath());
    }

    @Test
    void createDayPlanDataAccessDayplanDAO() {
        UserDataAccessSetup dataAccessSetup = new UserDataAccessSetup();
        DayPlanDataAccessInterface dayplanDAO = dataAccessSetup.createDayPlanDataAccess();

        assertNotNull(dayplanDAO);
        assertTrue(dayplanDAO instanceof DayPlanDataAccessObject);

        DayPlanDataAccessObject concreteDAO = (DayPlanDataAccessObject) dayplanDAO;
        assertEquals("./src/main/resources/DayplanDatabase.csv", concreteDAO.getcsvPath());
    }
}
