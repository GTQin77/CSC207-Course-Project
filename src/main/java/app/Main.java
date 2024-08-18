package app;

import app.setup.*;
import interface_adapter.ViewManagerModel;
import services.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Main function that runs the program.
     * @param args Arbitrary Input.
     */
    public static void main(String[] args) {
        JFrame application = setupMainFrame();
        JPanel views = setupViewPanel(application);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CardLayout cardLayout = (CardLayout) views.getLayout();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        initializeServicesAndViews(viewManagerModel, viewManager, views);

        application.pack();
        application.setVisible(true);
    }

    private static JFrame setupMainFrame() {
        JFrame application = new JFrame("Planify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(800, 600);
        application.setLocationRelativeTo(null);
        return application;
    }

    private static JPanel setupViewPanel(JFrame application) {
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views, BorderLayout.CENTER);
        return views;
    }

    private static void initializeServicesAndViews(ViewManagerModel viewManagerModel, ViewManager viewManager, JPanel views) {
        SetupDataAccessAndServices dataAccessAndServices = new SetupDataAccessAndServices();
        dataAccessAndServices.setup(viewManagerModel, viewManager, views);
    }
}

/**
 * Handles the setup of data access objects and services
 */
class SetupDataAccessAndServices {
    void setup(ViewManagerModel viewManagerModel, ViewManager viewManager, JPanel views) {
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
