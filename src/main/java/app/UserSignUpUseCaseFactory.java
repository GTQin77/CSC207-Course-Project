package app;

import data_access.UserSignupDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupViewModel;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInteractor;
import use_case.user_signup.UserSignupOutputBoundary;
import view.UserSignupView;


/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * */
public class UserSignUpUseCaseFactory {

    private UserSignUpUseCaseFactory() {}

    /**
     * Create method that controls flow of Use Case.
     * @param signupViewModel View model for User signup info.
     * @return a UserSignupView.
     */
    public static UserSignupView create(SignupViewModel signupViewModel) {
        SignupController controller = createUserSignUpCase(signupViewModel);
        UserSignupView userSignupView = new UserSignupView(signupViewModel, controller);
        userSignupView.setUser(controller.getUser());
        return userSignupView;
    }

    /**
     * Helper method for create that instantiates Interactor and relevant factories.
     * @param signupViewModel View model for User signup info.
     * @return a SignupController.
     */
    private static SignupController createUserSignUpCase(SignupViewModel signupViewModel) {
        UserSignupDataAccessObject userDataAccessObject = new UserSignupDataAccessObject();
        userDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");

        UserSignupOutputBoundary signupOutputBoundary = new SignupPresenter();

        UserFactory userFactory = new CommonUserFactory();

        UserSignupInputBoundary userSignupInteractor = new UserSignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory
        );
        SignupController signupController = new SignupController(userSignupInteractor);
        return signupController;
    }
}
