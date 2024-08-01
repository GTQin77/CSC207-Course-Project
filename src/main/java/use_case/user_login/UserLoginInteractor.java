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
        // 1. Retrieve User object from the DAO
        User user = this.userLoginDataAccessInterface.findUserByUsernameAndPassword(input.getUsername(), input.getPassword());

        // 2. Check if the user exists and if the password matches
        if (user != null) {
            UserLoginOutputData loginOutputData = new UserLoginOutputData(user, true);
            loginOutputBoundary.presentLoginResult(loginOutputData);
            return user;
        } else {
            loginOutputBoundary.presentLoginResult(new UserLoginOutputData(null, false));
            return null;
        }
    }
}

