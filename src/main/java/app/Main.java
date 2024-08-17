package app;

import api.OpenAI;
import api.OpenInterface;
import api.YelpFusion;
import api.YelpInterface;
import data_access.*;
import entity.*;
import interface_adapter.BusinessDetails.BusinessDetailsController;
import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.BusinessDetails.BusinessDetailsViewModel;
import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanPresenter;
import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.EditInfo.EditInfoViewModel;

import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import org.jetbrains.annotations.NotNull;
import services.RefreshService;
import services.UserService;
import use_case.refresh.RefreshInteractor;
import view.*;


import interface_adapter.Login.LoginViewModel;
import view.SignupView;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Main function that runs the program.
     * @param args Arbitrary Input.
     */
    public static void main(String[] args) {
        JFrame application = new JFrame("Planify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        DayplanInputViewModel dayplanInputViewModel = new DayplanInputViewModel();
        EditInfoViewModel editInfoViewModel = new EditInfoViewModel();
        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        BusinessDetailsViewModel businessDetailsViewModel = new BusinessDetailsViewModel();


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        UserSignupDataAccessInterface userDAO = new UserSignupDataAccessObject();
        DayPlanDataAccessInterface dayplanDAO = new DayPlanDataAccessObject();
        ((UserSignupDataAccessObject) userDAO).setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");

        ((DayPlanDataAccessObject) dayplanDAO).setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
        DayplanFactory dayplanFactory = new CommonDayplanFactory();

        UserService userService = new UserService(userDAO,dayplanDAO);


        RefreshService refreshService = getRefreshService(dayplanDAO, dayplanFactory);
        BusinessDetailsPresenter businessDetailsPresenter = new BusinessDetailsPresenter(viewManagerModel, businessDetailsViewModel, userService);
        DayplanController dayplanController = new DayplanController(dayplanViewModel, viewManager, userService, refreshService);
        DayplanPresenter dayplanPresenter = new DayplanPresenter(viewManagerModel, userService);


        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userService);
        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, signupViewModel, userService);
        DayplanInputView dayplanInputView = DayplanInputUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel,dayplanViewModel, userService);
        EditInfoView editInfoView = EditInfoUseCaseFactory.create(viewManagerModel, editInfoViewModel, userService);
        BusinessDetailsView businessDetailsView = new BusinessDetailsView(businessDetailsPresenter);
        BusinessDetailsController businessDetailsController = new BusinessDetailsController(businessDetailsViewModel, businessDetailsView);

        DayplanView dayplanView = new DayplanView(userService, dayplanPresenter, dayplanController, businessDetailsPresenter, businessDetailsController);
        userService.addPropertyChangeListener(dayplanView);
        dayplanController.setView(dayplanView);



        views.add(signupView, signupView.viewName);
        views.add(loginView, loginView.viewName);
        views.add(dayplanInputView, dayplanInputView.viewName);
        views.add(editInfoView, editInfoView.viewName);
        views.add(businessDetailsView, businessDetailsView.viewName);
        views.add(dayplanView, dayplanView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

    private static @NotNull RefreshService getRefreshService(DayPlanDataAccessInterface dayplanDAO, DayplanFactory dayplanFactory) {
        OpenInterface openApi = new OpenAI();
        YelpInterface yelpApi = new YelpFusion();
        YelpFusion yelpFusion = new YelpFusion();
        BusinessFactory businessFactory = new YelpBusinessFactory(yelpFusion);

        RefreshBusinessFactory refreshBusinessFactory = new CommonRefreshBusinessFactory(openApi, yelpApi, businessFactory);


        RefreshInteractor refreshInteractor = new RefreshInteractor(dayplanDAO, dayplanFactory, refreshBusinessFactory);
        RefreshService refreshService = new RefreshService(refreshInteractor);
        return refreshService;
    }
}