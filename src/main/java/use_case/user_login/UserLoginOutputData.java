package use_case.user_login;

import entity.User;

/**
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
