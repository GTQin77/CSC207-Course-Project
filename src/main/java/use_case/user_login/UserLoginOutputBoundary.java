package use_case.user_login;

/**
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupOutputBoundary on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputBoundary.java">github.com</a>.
 * </p>
 * Output boundary of the user login use case.
 */
public interface UserLoginOutputBoundary {

    void prepareSuccessView(UserLoginOutputData user);

    void prepareFailView(String error);
}
