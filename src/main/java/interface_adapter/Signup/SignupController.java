package interface_adapter.Signup;

import entity.User;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInteractor;
import use_case.user_signup.UserSignupInputData;

/**
 * Controller of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupController on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
 */
public class SignupController {
    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    private User user;

    final UserSignupInputBoundary userSignupInteractor;
    public SignupController(UserSignupInputBoundary userSignupInteractor) {
        this.userSignupInteractor = userSignupInteractor;
    }

    /**
     * Controller for sign up use case that calls Interactor's execute method.
     * @param username from input
     * @param password1 from input
     * @param password2 from input, repeated password
     */
    public void execute(String username, String password1, String password2, String location) {
        UserSignupInputData signupInputData = new UserSignupInputData(
                username, password1, password2, location);

        User user = userSignupInteractor.execute(signupInputData);

        this.setUser(user);
    }
}
