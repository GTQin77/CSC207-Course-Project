package use_case.user_login;

import entity.User;

/**
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupInputBoundary on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputBoundary.java">github.com</a>.
 * </p>
 * Output data of the user login use case.
 */
public class UserLoginOutputData {
    private final User user;
    private final boolean loginSuccessful;

    /**
     *
     * @param user The user object if login is successful, null otherwise.
     * @param loginSuccessful Whether the login was successful.
     */
    public UserLoginOutputData(User user, boolean loginSuccessful) {
        this.user = user;
        this.loginSuccessful = loginSuccessful;
    }

    public User getUser() {
        return user;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
