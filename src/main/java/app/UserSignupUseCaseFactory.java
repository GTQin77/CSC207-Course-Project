package app;

import data_access.UserSignupDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.Signup.*;
import interface_adapter.Login.*;
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

    public static SignupView create(SignupViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(SignupViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
        UserSignupDataAccessObject userDataAccessObject = new UserSignupDataAccessObject();
        userDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        // Notice how we pass this method's parameters to the Presenter.
        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

}