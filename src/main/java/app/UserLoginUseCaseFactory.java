package app;

import data_access.UserLoginDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.*;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import services.UserService;
import use_case.user_login.UserLoginInputBoundary;
import use_case.user_login.UserLoginInteractor;
import use_case.user_login.UserLoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;


/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * <p>
 *  Responsible for creating and assembling the components necessary for the user login use case.</p>
 * */

public class UserLoginUseCaseFactory {

    /** Prevent instantiation. */
    private UserLoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, SignupViewModel signupViewModel, UserService userService) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, dayplanInputViewModel, userService);
            return new LoginView(loginViewModel, viewManagerModel, signupViewModel, loginController, dayplanInputViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, UserService userService) throws IOException {
        UserLoginDataAccessObject userDataAccessObject = new UserLoginDataAccessObject();
        userDataAccessObject.setcsvFileandPath("./src/main/resources/UserDatabase.csv");

        UserLoginOutputBoundary loginOutputBoundary = new UserLoginPresenter(viewManagerModel, loginViewModel, dayplanInputViewModel);

        UserFactory userFactory = new CommonUserFactory();

        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(userLoginInteractor, userService);
    }

}
