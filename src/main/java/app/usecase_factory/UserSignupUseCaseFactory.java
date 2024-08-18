package app.usecase_factory;

import data_access.UserSignupDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.Signup.*;
import interface_adapter.Login.*;
import interface_adapter.ViewManagerModel;
//import interface_adapter.Welcome.WelcomeViewModel;
import services.UserService;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInteractor;
import use_case.user_signup.UserSignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;


/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * */

public class UserSignupUseCaseFactory {

    /** Prevent instantiation. */
    private UserSignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, UserService userService) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userService);
            return new SignupView(signupController, signupViewModel, viewManagerModel, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, UserService userService) throws IOException {
        UserSignupDataAccessObject userDataAccessObject = new UserSignupDataAccessObject();
        userDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userService, userSignupInteractor);
    }

}