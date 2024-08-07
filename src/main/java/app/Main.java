package app;

import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Welcome.WelcomeViewManagerModel;
import interface_adapter.Welcome.WelcomeViewModel;
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
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Planify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
        DayplanInputViewModel dayplanInputViewModel = new DayplanInputViewModel();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, welcomeViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, signupViewModel);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);



//        // This keeps track of and manages which view is currently showing.
//        WelcomeViewManagerModel welcomeViewManagerModel = new WelcomeViewManagerModel();
//        new ViewManager(views, cardLayout, welcomeViewManagerModel);
//
//        // The data for the views, such as username and password, are in the ViewModels.
//        // This information will be changed by a presenter object that is reporting the
//        // results from the use case. The ViewModels are observable, and will
//        // be observed by the Views.
//        WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//
//        interface_adapter.ViewManagerModel viewManagerModel = new interface_adapter.ViewManagerModel();
//        ViewManagerModel loginViewManagerModel = new ViewManagerModel();
//
//
//        WelcomeView welcomeView = new WelcomeView(welcomeViewModel, loginViewModel, signupViewModel, welcomeViewManagerModel);
//        views.add(welcomeView, welcomeView.viewName);
//
//
//        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, welcomeViewModel);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = new LoginView(loginViewModel);
//        views.add(loginView, loginView.viewName);
//
//        welcomeViewManagerModel.setActiveView(welcomeView.viewName);
//        welcomeViewManagerModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(signupView.viewName);
//        viewManagerModel.firePropertyChanged();
//
//        application.pack();
//        application.setVisible(true);
    }
}