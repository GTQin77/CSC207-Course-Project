package interface_adapter.Login;

import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;

public class LoginPresenter implements UserLoginOutputBoundary {

    /**
     * @param user User object returned by the login interactor if login is successful.
     */
    @Override
    public void prepareSuccessView(UserLoginOutputData user) {
        System.out.println("Login success for user: " + user.getUser());
    }

    /**
     * @param error Error message if login fails.
     */
    @Override
    public void prepareFailView(String error) {
        System.out.println("Login failed: " + error);
    }
}
