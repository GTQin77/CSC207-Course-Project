package interface_adapter;

import use_case.user.UserSignupInputBoundary;
import use_case.user.UserSignupInputData;

import java.util.ArrayList;

/**
 * Controller of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupController on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
 */
public class SignupController {
    final UserSignupInputBoundary userSignupInteractor;

    public SignupController(UserSignupInputBoundary userSignupInteractor) {
        this.userSignupInteractor = userSignupInteractor;
    }

    public void execute(String username, String password1, String password2) {
        UserSignupInputData signupInputData = new UserSignupInputData(
                username, password1, password2); // not sure what bug is this

        userSignupInteractor.execute(signupInputData);
    }
}
