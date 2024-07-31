package interface_adapter.Signup;

import use_case.user_signup.UserSignupOutputBoundary;
import use_case.user_signup.UserSignupOutputData;

public class SignupPresenter implements UserSignupOutputBoundary {

    /**
     * @param user User object created by the signup interactor.
     */
    @Override
    public void prepareSuccessView(UserSignupOutputData user) {
        System.out.println("Signup success!");
    }

    /**
     * @param error Error message from the signup interactor.
     */
    @Override
    public void prepareFailView(String error) {
        System.out.println("Signup failed!" + error);
    }
}
