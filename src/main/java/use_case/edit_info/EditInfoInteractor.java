package use_case.edit_info;

import data_access.EditInfoDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import entity.User;
import entity.UserFactory;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.user_signup.UserSignupInputData;
import use_case.user_signup.UserSignupOutputBoundary;
import use_case.user_signup.UserSignupOutputData;

public class EditInfoInteractor {
    final EditInfoDataAccessInterface editInfoDAO;
    final EditInfoOutputBoundary editInfoPresenter;
    final UserFactory userFactory;
    private User user;

    public String getNewUsername() {return newUsername;}

    public void setNewUsername(String newUsername) {this.newUsername = newUsername;}

    public String getNewPassword() {return newPassword;}

    public void setNewPassword(String newPassword) {this.newPassword = newPassword;}

    public String getNewLocation() {return newLocation;}

    public void setNewLocation(String newLocation) {this.newLocation = newLocation;}

    private String newUsername;
    private String newPassword;
    private String newLocation;

    public EditInfoInteractor(EditInfoDataAccessInterface editInfoDataAccessObject,
                              EditInfoOutputBoundary editInfoPresenter, UserFactory userFactory, User user){
        this.editInfoDAO= editInfoDataAccessObject;
        this.editInfoPresenter = editInfoPresenter;
        this.userFactory = userFactory;
        this.user = user;
    }

    public User execute(EditInfoInputData input){
        // 0. TO DO: Process input data... if string is empty, set it to current user info
        // Considering cases...

        // CASE 1. all fields empty/only repeatPassword is filled
        if (input.getUserName().isEmpty() && input.getLocation().isEmpty() && input.getPassword().isEmpty()) { // just return current this.user
            EditInfoOutputData editInfoOutputData = new EditInfoOutputData(user, );
            editInfoPresenter.prepareSuccessView("No changes made. Continue program.");
        }
        // CASE 2. passwords don't match
        else if (!input.getPassword().equals(input.getRepeatPassword())){} // prepare presenter fail view

        // CASE 3. Username and/or others have ben changed
        else if (!user.getUserName().equals(input.getUserName())){} // Call DAO.editUsername

        // CASE 4. Username hasn't been changed, but at least one other thing has been changed
        else{}








        this.processInput(input);
        // 1. Process input data... do passwords match or not?
        if (input.getPassword().equals(input.getRepeatPassword())){
            // 2. Evaluate booleans... usernameChanged, passwordChanged, etc.
            // Username CHANGED
            if (!user.getUserName().equals(input.getUserName())){
                editInfoDAO.editUsername(input.getUserName(), input.getPassword(), input.getRepeatPassword());
            }
            // 3. Depending on boolean, call either username function or password/location function in DAO

            // 4. Create new User object and create output boundary carrying that user
        } else{
            editInfoPresenter.prepareFailView();
        }






        return user2;
    }
    private void processInput(EditInfoInputData input){
        // If a string is empty, set it to the current user attributes
        if
        if (input.getUserName().isEmpty()){this.setNewUsername(user.getUserName());}    // setting current username to old username
        if (input.getPassword().isEmpty()){this.setNewPassword(user.getPassword());}
        if (input.getRepeatPassword().isEmpty()){this.setNewPassword(user.getPassword());}
        // REMOVE SPACING IN LOCATION COMMAS!!!!!


    }






}
