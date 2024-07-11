package use_case;

import data_access.UserSignupDataAccessInterface;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

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
    public void execute(UserSignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
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