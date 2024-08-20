package app.setup;

import interface_adapter.ViewManagerModel;
import services.RefreshService;
import services.UserService;
import view.ViewManager;

import javax.swing.*;

/**
 * Handles the setup of data access objects and services
 */
public class SetupDataAccessAndServices {
    public void setup(ViewManagerModel viewManagerModel, ViewManager viewManager, JPanel views) {
        UserDataAccessSetup dataAccessSetup = new UserDataAccessSetup();
        ServiceFactory serviceFactory = new ServiceFactory(
                dataAccessSetup.createUserSignupDataAccess(),
                dataAccessSetup.createDayPlanDataAccess()
        );

        UserService userService = serviceFactory.createUserService();
        RefreshService refreshService = serviceFactory.createRefreshService();

        ViewSetup viewSetup = new ViewSetup(viewManagerModel, userService, refreshService);
        viewSetup.setupViews(views);

        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();
    }
}