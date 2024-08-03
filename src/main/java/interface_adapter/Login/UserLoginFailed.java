package interface_adapter.Login;

public class UserLoginFailed extends RuntimeException {
    public UserLoginFailed(String message) {
        super(message);
    }
}
