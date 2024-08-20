package app.usecase_factory;

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

    /**
     * Creates and returns a {@link LoginView} object, initializing all necessary components
     * for the user login use case.
     *
     * @param viewManagerModel the model managing different views in the application
     * @param loginViewModel the model holding data for the login view
     * @param dayplanInputViewModel the model holding data for the day plan input view
     * @param signupViewModel the model holding data for the signup view
     * @param userService the service responsible for user-related operations
     * @return a fully initialized {@code LoginView} object, or {@code null} if an exception occurs
     */

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, SignupViewModel signupViewModel, UserService userService) {

        LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, dayplanInputViewModel, userService);
        return new LoginView(loginViewModel, viewManagerModel, signupViewModel, loginController);

    }

    /**
     * Creates and returns a {@link LoginController} object, initializing all necessary components
     * for the user login use case, including the data access object, interactor, and presenter.
     *
     * @param viewManagerModel the model managing different views in the application
     * @param loginViewModel the model holding data for the login view
     * @param dayplanInputViewModel the model holding data for the day plan input view
     * @param userService the service responsible for user-related operations
     * @return a fully initialized {@code LoginController} object
     * @throws IOException if there is an error accessing the user data file
     */

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, UserService userService) {
        UserLoginDataAccessObject userDataAccessObject = new UserLoginDataAccessObject();
        userDataAccessObject.setcsvFileandPath("./src/main/resources/UserDatabase.csv");

        UserLoginOutputBoundary loginOutputBoundary = new UserLoginPresenter(viewManagerModel, loginViewModel, dayplanInputViewModel);

        UserFactory userFactory = new CommonUserFactory();

        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(userLoginInteractor, userService);
    }

}
