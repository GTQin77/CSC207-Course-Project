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
    private String loginTime;

    /**
     *
     * @param user The user object if login is successful, null otherwise.
     * @param loginSuccessful Whether the login was successful.
     * @param loginTime When the login occurred.
     */
    public UserLoginOutputData(User user, boolean loginSuccessful, String loginTime) {
        this.user = user;
        this.loginSuccessful = loginSuccessful;
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
