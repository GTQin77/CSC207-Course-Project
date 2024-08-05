package interface_adapter.Login;

import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter of the user sign up use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignUpPresenter on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
 * Handles the presentation logic for user login.
 * <p>
 * This presenter formats the login time for successful logins
 * and throws an exception for failed logins.
 */

public class UserLoginPresenter implements UserLoginOutputBoundary {

    /**
     * Prepares the success view by formatting the login time.
     *
     * @param response The data for the successful login, including user information and login time.
     */

    @Override
    public void prepareSuccessView(UserLoginOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    }

    /**
     * Prepares the failure view by throwing an exception with the given error message.
     *
     * @param error The error message to be included in the exception.
     * @throws UserLoginFailed when the login process fails.
     */

    @Override
    public void prepareFailView(String error) {
        throw new UserLoginFailed(error);
    }
}
