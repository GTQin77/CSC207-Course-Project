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
