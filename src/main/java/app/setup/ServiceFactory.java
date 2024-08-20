package app.setup;

import api.*;

import data_access.DayPlanDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import entity.*;
import services.*;
import use_case.refresh.RefreshInteractor;

public class ServiceFactory {
    private final UserSignupDataAccessInterface userDAO;
    private final DayPlanDataAccessInterface dayplanDAO;

    /**
     * Constructs a new instance of ServiceFactory with specific data access interfaces.
     *
     * @param userDAO the data access interface for user data.
     * @param dayplanDAO the data access interface for dayplan data.
     */
    public ServiceFactory(UserSignupDataAccessInterface userDAO, DayPlanDataAccessInterface dayplanDAO) {
        this.userDAO = userDAO;
        this.dayplanDAO = dayplanDAO;
    }

    public UserService createUserService() {
        return new UserService(userDAO, dayplanDAO);
    }

    public RefreshService createRefreshService() {
        DayplanFactory dayplanFactory = new CommonDayplanFactory();
        return getRefreshService(dayplanDAO, dayplanFactory);
    }

    /**
     * Constructs a {@link RefreshService} using specified data access objects and a business factory.
     *
     * @param dayplanDAO the data access interface for dayplan data.
     * @param dayplanFactory the factory used for creating day plan entities.
     * @return an instance of {@link RefreshService}.
     */
    private RefreshService getRefreshService(DayPlanDataAccessInterface dayplanDAO, DayplanFactory dayplanFactory) {
        OpenInterface openApi = new OpenAI();
        YelpInterface yelpApi = new YelpFusion();
        YelpFusion yelpFusion = new YelpFusion();
        BusinessFactory businessFactory = new YelpBusinessFactory(yelpFusion);
        RefreshBusinessFactory refreshBusinessFactory = new CommonRefreshBusinessFactory(openApi, yelpApi, businessFactory);
        RefreshInteractor refreshInteractor = new RefreshInteractor(dayplanDAO, dayplanFactory, refreshBusinessFactory);
        return new RefreshService(refreshInteractor);
    }
}
