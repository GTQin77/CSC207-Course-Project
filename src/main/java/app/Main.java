package app;

import interface_adapter.DayplanViewModel;
import interface_adapter.SignupViewModel;
import view.DayplanView;
import view.UserSignupView;

public class Main {
    public static void main(String[] args) {
        SignupViewModel signupViewModel = new SignupViewModel();
        UserSignupView userSignupView = UserSignUpUseCaseFactory.create(signupViewModel);

        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        dayplanViewModel.setUser(userSignupView.getUser());
        DayplanView dayplanView = DayPlanUseCaseFactory.create(userSignupView.getUser(), dayplanViewModel);
    }
}
