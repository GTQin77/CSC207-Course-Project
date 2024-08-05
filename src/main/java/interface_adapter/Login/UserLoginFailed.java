package interface_adapter.Login;

/**
 * UserLoginFailed for the login use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for UserLoginFailed on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class UserLoginFailed extends RuntimeException {
    public UserLoginFailed(String message) {
        super(message);
    }
}
