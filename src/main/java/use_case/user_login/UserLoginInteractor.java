package use_case.user_login;

import data_access.UserLoginDataAccessInterface;
import entity.User;
import entity.UserFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserLoginInteractor implements UserLoginInputBoundary {
    final UserLoginDataAccessInterface userDataAccessInterface;
    final UserLoginOutputBoundary userPresenter;
    final UserFactory userFactory;

    /**
     * Interactor of the user login use case.
     *
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for SignupInteractor on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInteractor.java">github.com</a>.
     * </p>
     * @param userLoginDataAccessInterface Data access interface of the user login use case.
     * @param userFactory Factory for user.
     * @param userLoginOutputBoundary Output boundary of the login use case.
     */

    public UserLoginInteractor(UserLoginDataAccessInterface userLoginDataAccessInterface, UserLoginOutputBoundary userLoginOutputBoundary,
                               UserFactory userFactory) {
        this.userDataAccessInterface = userLoginDataAccessInterface;
        this.userPresenter = userLoginOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     *
     * Handles the login process for a user
     *
     * @param userLoginInputData The data needed for user login, including username and password.
     */

    @Override
    public User loginUser(UserLoginInputData userLoginInputData) {
        boolean userExists = userDataAccessInterface.findUser(userLoginInputData.getUsername(), userLoginInputData.getPassword());
        boolean usernameExists = userDataAccessInterface.findUser(userLoginInputData.getUsername(), userLoginInputData.getPassword());

        if (userExists) {
            if (usernameExists) {
                userPresenter.prepareFailView("Invalid password.");
            } else {
                userPresenter.prepareFailView("Incorrect username.");
            }
            return null;
        }
        User user = userDataAccessInterface.getUser(userLoginInputData.getUsername(), userLoginInputData.getPassword());
        LocalDateTime now = LocalDateTime.now();
        String loginTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        UserLoginOutputData loginResponseModel = new UserLoginOutputData(user, true, loginTime);
        userPresenter.prepareSuccessView(loginResponseModel);
        return user;
    }
}

