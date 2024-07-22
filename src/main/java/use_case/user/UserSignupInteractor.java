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

    /**
     * Interactor of the user sign up use case.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for SignupInteractor on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInteractor.java">github.com</a>.
     * </p>
     * @param signupOutputBoundary Output boundary of the signup use case.
     * @param userFactory Factory for user.
     * @param userSignupDataAccessInterface Data access interface of the user signup use case.
     */
    public UserSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            UserSignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(UserSignupInputData input) {
        // 1. Process InputData into correct data types
        ArrayList<Double> location = new ArrayList<Double>();
        for (int i = 0; i < input.getLocation().size(); i++){
            location.add(Double.valueOf(input.getLocation().get(i)));
        }
        // 2. Create new User object using UserFactory
        User user = this.userFactory.create(input.getUsername(), input.getPassword(), location);
        // 3. Write new User to the DAO
        if (this.userDataAccessObject.userExists(user)){
            // NOTE TO SELF: CHANGE DAO implementation to only take username!!!
            // So that we avoid having to create a new User object until else block
            userPresenter.prepareFailView("Oops! This username already exists.");
        } else if (!input.getPassword().equals(input.getRepeatPassword())){
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            LocalDateTime now = LocalDateTime.now();
            this.userDataAccessObject.saveUser(user);
            UserSignupOutputData signupOutputData = new UserSignupOutputData(user, now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}