package interface_adapter.EditInfo;

import entity.User;
import services.UserService;
import use_case.edit_info.EditInfoInputBoundary;
import use_case.edit_info.EditInfoInteractor;
import use_case.edit_info.EditInfoInputData;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInputData;

import java.util.ArrayList;
import java.util.Objects;

public class EditInfoController {
    private User user;
    private UserService userService;

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    final EditInfoInputBoundary editInfoInteractor;

    public EditInfoController(UserService userService, EditInfoInputBoundary editInfoInteractor) {
        this.userService = userService;
        this.editInfoInteractor = editInfoInteractor;
    }

    /**
     * Controller for edit user info use case that calls Interactor's execute method.
     * @param username from input
     * @param password1 from input
     * @param password2 from input, repeated password
     * @param location from input.
     */
    public void execute(String username, String password1, String password2, String location, UserService userService) {
        User user1 = userService.getCurrentUser();
        if (Objects.equals(username, "")){username = userService.getCurrentUser().getUserName();}    // setting current username to old username
        if (Objects.equals(password1, "")){password1 = user1.getPassword();}
        if (Objects.equals(password2, "")){password2 = user1.getPassword();}

        ArrayList<Double> coordinates = user1.getLocation();
        String reLocation = coordinates.getFirst() + "," + coordinates.getLast();
        if (Objects.equals(location, "")){location = reLocation;}

        EditInfoInputData editInfoInputData = new EditInfoInputData(
                username, password1, password2, location, user);
        this.user = editInfoInteractor.execute(editInfoInputData, userService);
        this.setUser(user);
        userService.setCurrentUser(user);
    }

}
