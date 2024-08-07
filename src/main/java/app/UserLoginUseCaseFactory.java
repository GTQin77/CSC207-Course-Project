package app;

import data_access.UserLoginDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.*;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.user_login.UserLoginInputBoundary;
import use_case.user_login.UserLoginInteractor;
import use_case.user_login.UserLoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;


/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * */

public class UserLoginUseCaseFactory {

    /** Prevent instantiation. */
    private UserLoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, SignupViewModel signupViewModel) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, dayplanInputViewModel);
            return new LoginView(loginViewModel, viewManagerModel, signupViewModel, loginController, dayplanInputViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel) throws IOException {
        UserLoginDataAccessObject userDataAccessObject = new UserLoginDataAccessObject();
        userDataAccessObject.setcsvFileandPath("./src/main/resources/UserDatabase.csv");

        UserLoginOutputBoundary loginOutputBoundary = new UserLoginPresenter(viewManagerModel, loginViewModel, dayplanInputViewModel);

        UserFactory userFactory = new CommonUserFactory();

        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(userLoginInteractor);
    }

}
