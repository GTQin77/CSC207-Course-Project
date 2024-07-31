package use_case.user_signup;

/**
 * Output boundary of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupOutputBoundary on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupOutputBoundary.java">github.com</a>.
 * </p>
 */
public interface UserSignupOutputBoundary {
    void prepareSuccessView(UserSignupOutputData user);

    void prepareFailView(String error);
}