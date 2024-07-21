package use_case.user;

import data_access.UserSignupDataAccessInterface;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSignupInteractor implements UserSignupInputBoundary {
    final UserSignupDataAccessInterface userDataAccessObject;
    final UserSignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public UserSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            UserSignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(UserSignupInputData input) {
        // 1. Create User using UserFactory and Input Data
        ArrayList<Double> location = new ArrayList<Double>(2);

        User user = this.userFactory.create(input.getUsername(), ArrayList<Double> location, String mood,
        int numActivities, int numMeals);




        if (userDataAccessObject.userExists(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            UserSignupOutputData signupOutputData = new UserSignupOutputData(user.getUserName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}