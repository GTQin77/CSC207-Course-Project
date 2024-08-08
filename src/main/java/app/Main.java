package app;

import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.BusinessDetails.BusinessDetailsViewModel;
import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.EditInfo.EditInfoViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Welcome.WelcomeViewManagerModel;
import interface_adapter.Welcome.WelcomeViewModel;
import services.RefreshService;
import services.UserService;
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
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        DayplanInputViewModel dayplanInputViewModel = new DayplanInputViewModel();
        EditInfoViewModel editInfoViewModel = new EditInfoViewModel();
        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        BusinessDetailsViewModel businessDetailsViewModel = new BusinessDetailsViewModel();


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);

        UserService userService = new UserService();
        RefreshService refreshService = new RefreshService();
        BusinessDetailsPresenter businessDetailsPresenter = new BusinessDetailsPresenter(viewManagerModel);
        DayplanController dayplanController = new DayplanController(dayplanViewModel, viewManager, userService, refreshService);

        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, welcomeViewModel, userService);
        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, signupViewModel, userService);
        DayplanInputView dayplanInputView = DayplanInputUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel,dayplanViewModel, userService);
        EditInfoView editInfoView = EditInfoUseCaseFactory.create(viewManagerModel, editInfoViewModel, userService);
        BusinessDetailsView businessDetailsView = new BusinessDetailsView(businessDetailsPresenter);
        DayplanView dayplanView = new DayplanView(businessDetailsView, dayplanController, userService);

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
}