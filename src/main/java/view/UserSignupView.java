package view;

import entity.User;
import interface_adapter.SignupController;
import interface_adapter.SignupViewModel;

import java.util.Scanner;

public class UserSignupView {
    private final SignupViewModel signupViewModel;
    private final SignupController signupController;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;



    public UserSignupView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        signupViewModel.setUserName();
        String userName = signupViewModel.getUserName();

        signupViewModel.setPassword();
        String password = signupViewModel.getPassword();

        signupViewModel.setRepeatedPassword();
        String repeatedPassword = signupViewModel.getRepeatedPassword();

        signupController.execute(userName, password, repeatedPassword);
    }

}
