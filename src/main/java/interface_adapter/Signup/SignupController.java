package interface_adapter.Signup;

import entity.User;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInteractor;
import use_case.user_signup.UserSignupInputData;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupController on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
 */
public class SignupController {

    private User user;
    private ArrayList<String> location;

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public ArrayList<String> getLocation() {return location;}
    public void setLocation(ArrayList<String> location) {this.location = location;}

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
        if (Objects.equals(location, "")) {
            location = "0;0";
        }
        UserSignupInputData signupInputData = new UserSignupInputData(
                username, password1, password2, location);

        User user = userSignupInteractor.execute(signupInputData);

        this.setUser(user);
    }
}
