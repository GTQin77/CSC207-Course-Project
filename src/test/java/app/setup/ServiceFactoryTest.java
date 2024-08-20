package app.setup;

import static org.junit.jupiter.api.Assertions.*;

import data_access.DayPlanDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import org.junit.jupiter.api.Test;
import services.RefreshService;
import services.UserService;

import org.junit.jupiter.api.BeforeEach;

class ServiceFactoryTest {
    private UserDataAccessSetup userDataAccessSetup;
    private UserSignupDataAccessInterface userDAO;
    private DayPlanDataAccessInterface dayplanDAO;
    private ServiceFactory serviceFactory;

    @BeforeEach
    void setUp() {
        userDataAccessSetup = new UserDataAccessSetup();
        userDAO = userDataAccessSetup.createUserSignupDataAccess();
        dayplanDAO = userDataAccessSetup.createDayPlanDataAccess();
        serviceFactory = new ServiceFactory(userDAO, dayplanDAO);
    }

    @Test
    void testCreateUserService() {
        UserService userService = serviceFactory.createUserService();
        assertNotNull(userService);
    }

    @Test
    void testCreateRefreshService() {
        RefreshService refreshService = serviceFactory.createRefreshService();
        assertNotNull(refreshService);
    }
}

