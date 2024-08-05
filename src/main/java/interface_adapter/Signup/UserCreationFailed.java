package interface_adapter.Signup;

/**
 * User creation failed scenario.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for UserCreationFailed on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class UserCreationFailed extends RuntimeException {
    public UserCreationFailed(String error) {
        super(error);
    }
}