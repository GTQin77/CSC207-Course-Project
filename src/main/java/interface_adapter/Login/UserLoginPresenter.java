package interface_adapter.Login;

import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter of the user Login use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignUpPresenter on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/interface_adapter/SignupController.java">github.com</a>.
 * </p>
 * Handles the presentation logic for user login.
 * <p>
 * This presenter formats the login time for successful logins
 * and throws an exception for failed logins.
 */

import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoginViewManagerModel viewManagerModel;

    public UserLoginPresenter(LoginViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginOutputData response) {
        // On success, switch to the main application view or dashboard.
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUser().getUserName());
        loginState.setLoginTime(response.getLoginTime());
        loginState.setLoginSuccessful(true);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("mainView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginState.setLoginSuccessful(false);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }
}