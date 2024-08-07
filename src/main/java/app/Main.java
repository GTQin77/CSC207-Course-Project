package app;

import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Welcome.WelcomeViewManagerModel;
import interface_adapter.Welcome.WelcomeViewModel;
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

        UserService userService = new UserService();

        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, welcomeViewModel, userService);
        views.add(signupView, signupView.viewName);

        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, signupViewModel, userService);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }
}