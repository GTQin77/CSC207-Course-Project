package interface_adapter.Signup;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import use_case.user_signup.UserSignupOutputBoundary;
import use_case.user_signup.UserSignupOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * SignupPresenter for the Signup use case.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for SignupPresenter on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class SignupPresenter implements UserSignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private SignupViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(UserSignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        SignupState signupState = signupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUser().getUserName());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}