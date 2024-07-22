package app;

import data_access.UserSignupDataAccessInterface;
import data_access.UserSignupDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.SignupController;
import interface_adapter.SignupPresenter;
import interface_adapter.SignupViewModel;
import use_case.user.UserSignupInputBoundary;
import use_case.user.UserSignupInteractor;
import use_case.user.UserSignupOutputBoundary;
import view.UserSignupView;


/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * */
public class UserSignUpUseCaseFactory {

    private UserSignUpUseCaseFactory() {}

    public static UserSignupView create(SignupViewModel signupViewModel) {
        SignupController controller = createUserSignUpCase(signupViewModel);
        return new UserSignupView(signupViewModel, controller);
    }

    private static SignupController createUserSignUpCase(SignupViewModel signupViewModel) {
        UserSignupDataAccessObject userDataAccessObject = new UserSignupDataAccessObject();
        userDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");

        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter();

        UserFactory userFactory = new CommonUserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory
        );
        return new SignupController(userSignupInteractor);
    }
}
