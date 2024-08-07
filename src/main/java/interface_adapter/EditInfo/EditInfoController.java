package interface_adapter.EditInfo;

import entity.User;
import use_case.edit_info.EditInfoInputBoundary;
import use_case.edit_info.EditInfoInteractor;
import use_case.edit_info.EditInfoInputData;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInputData;

import java.util.ArrayList;
import java.util.Objects;

public class EditInfoController {
    private User user;

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    final EditInfoInputBoundary editInfoInteractor;

    public EditInfoController(EditInfoInputBoundary editInfoInteractor) {
        this.editInfoInteractor = editInfoInteractor;
    }

    /**
     * Controller for edit user info use case that calls Interactor's execute method.
     * @param username from input
     * @param password1 from input
     * @param password2 from input, repeated password
     * @param location from input.
     */
    public void execute(String username, String password1, String password2, String location, User user) {
        EditInfoInputData editInfoInputData = new EditInfoInputData(
                username, password1, password2, location, user);

        this.user = editInfoInteractor.execute(editInfoInputData);

        this.setUser(user);
    }

}
