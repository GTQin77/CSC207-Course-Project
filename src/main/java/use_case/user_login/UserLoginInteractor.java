package use_case.user_login;

import data_access.UserLoginDataAccessInterface;
import entity.User;
import entity.UserFactory;

/**
 * Interactor of the user login use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupInputBoundary on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputBoundary.java">github.com</a>.
 * </p>
 */

public class UserLoginInteractor implements UserLoginInputBoundary {
    final UserLoginDataAccessInterface userLoginDataAccessInterface;
    final UserLoginOutputBoundary loginOutputBoundary;
    final UserFactory userFactory;
    private User user;


    /**
     * Constructor for the UserLoginInteractor
     *
     * @param userLoginDataAccessInterface Data access interface of the user login use case.
     * @param loginOutputBoundary Output boundary of the login use case.
     * @param userFactory Factory for user.
     */

    public UserLoginInteractor(UserLoginDataAccessInterface userLoginDataAccessInterface,
                               UserLoginOutputBoundary loginOutputBoundary,
                               UserFactory userFactory) {
        this.userLoginDataAccessInterface = userLoginDataAccessInterface;
        this.loginOutputBoundary = loginOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Interactor method for login use case that retrieves user and checks that it exists.
     * @param input UserLoginInputData.
     */
    @Override
    public User execute(UserLoginInputData input) {
        boolean userExists = this.userLoginDataAccessInterface.findUser(input.getUsername(), input.getPassword());

        if (!userExists) {
            loginOutputBoundary.prepareFailView("Password or username incorrect.");
            return null;
        } else {
            UserLoginOutputData loginOutputData = new UserLoginOutputData(user, true);
            User user = new User(input.getUsername(), input.getPassword(), input.getLocation());
            loginOutputBoundary.prepareSuccessView(loginOutputData);
            loginOutputBoundary.prepareSuccessView(loginOutputData);
            return user;
        }
    }
}

