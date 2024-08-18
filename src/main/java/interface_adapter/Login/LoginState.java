package interface_adapter.Login;

/**
 * LoginState for the Login use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for LoginState on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class LoginState {
    private String username = "";
    private String errorMessage = null;
    private String password = "";
    private boolean loginSuccessful;
    private String loginTime;


    public LoginState(LoginState copy) {
        username = copy.username;
        errorMessage = copy.errorMessage;
        password = copy.password;
        loginSuccessful = copy.loginSuccessful;
        loginTime = copy.loginTime;
    }


    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginTime() { return loginTime; }

    public boolean loginSuccessful() { return loginSuccessful; }
}