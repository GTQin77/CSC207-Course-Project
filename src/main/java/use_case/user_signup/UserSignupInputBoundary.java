package use_case.user_signup;

/**
 * Input boundary of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupInputBoundary on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputBoundary.java">github.com</a>.
 * </p>
 */
public interface UserSignupInputBoundary {
    void execute(UserSignupInputData signupInputData);
}