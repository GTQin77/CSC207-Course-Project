package interface_adapter.Login;

import entity.User;
import use_case.user_login.UserLoginInputBoundary;
import use_case.user_login.UserLoginInputData;

/**
 * Controls the user login process.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for LoginController on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 * This class interacts with the user login interactor to handle login requests
 * and maintains the logged-in user state.
 */


public class LoginController {
    final UserLoginInputBoundary userLoginInteractor;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Constructs a LoginController with the specified user login interactor.
     *
     * @param userLoginInteractor The interactor to handle user login logic.
     */

    public LoginController(UserLoginInputBoundary userLoginInteractor) {
        this.userLoginInteractor = userLoginInteractor;
    }
    /**
     * Executes the login process with the provided username and password.
     *
     * @param username The username provided for login.
     * @param password The password provided for login.
     */

    public void execute(String username, String password) {
        UserLoginInputData loginInputData = new UserLoginInputData(username, password);
        User user = userLoginInteractor.loginUser(loginInputData);
        this.setUser(user);
    }
}
