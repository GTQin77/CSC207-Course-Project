package app;

import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.Signup.SignupViewModel;
import view.DayplanView;
import view.UserSignupView;

public class Main {

    /**
     * Main function that runs the program.
     * @param args Arbitrary Input.
     */
    public static void main(String[] args) {
        SignupViewModel signupViewModel = new SignupViewModel();
        UserSignupView userSignupView = UserSignUpUseCaseFactory.create(signupViewModel);

        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        dayplanViewModel.setUser(userSignupView.getUser());
        DayplanView dayplanView = DayPlanUseCaseFactory.create(userSignupView.getUser(), dayplanViewModel);
    }
}
