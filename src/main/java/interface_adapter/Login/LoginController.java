package interface_adapter.Login;

import entity.User;
import use_case.user_login.UserLoginInputBoundary;
import use_case.user_login.UserLoginInputData;

import java.util.ArrayList;

/**
 * Controller of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupController on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
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

    public LoginController(UserLoginInputBoundary userLoginInteractor) {
        this.userLoginInteractor = userLoginInteractor;
    }

    /**
     * Controller for login use case that calls Interactor's execute method.
     * @param username from input
     * @param password from input
     * @param location from input
     */
    public void execute(String username, String password, ArrayList<Double> location) {
        UserLoginInputData loginInputData = new UserLoginInputData(username, password, location);
        User user = userLoginInteractor.execute(loginInputData);
        this.setUser(user);
    }
}
