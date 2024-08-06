package interface_adapter.Login;

import interface_adapter.DayplanInput.DayplanInputViewModel;
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

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoginViewManagerModel viewManagerModel;
    private final DayplanInputViewModel dayplanInputViewModel;

    public UserLoginPresenter(LoginViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.dayplanInputViewModel = dayplanInputViewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginOutputData response) {
        // On success, switch to the dayplan input view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getLoginTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        response.setLoginTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUser().getUserName());
        loginState.setLoginTime(response.getLoginTime());
        loginState.setLoginSuccessful(true);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(dayplanInputViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginSuccessful(false);

        if (error.contains("username")) {
            loginState.setUsernameError("Incorrect username.");
        } else if (error.contains("password")) {
            loginState.setPasswordError("Incorrect password.");
        }

        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }
}