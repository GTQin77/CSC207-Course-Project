package use_case.edit_info;

import data_access.EditInfoDataAccessInterface;
import data_access.EditInfoDataAccessObject;

import entity.User;
import entity.UserFactory;
import services.UserService;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.user_signup.UserSignupInputData;
import use_case.user_signup.UserSignupOutputBoundary;
import use_case.user_signup.UserSignupOutputData;

import java.util.ArrayList;
import java.util.List;

public class EditInfoInteractor implements EditInfoInputBoundary{
    final EditInfoDataAccessInterface editInfoDAO;
    final EditInfoOutputBoundary editInfoPresenter;
    private User user;


    public EditInfoInteractor(EditInfoDataAccessInterface editInfoDataAccessObject,
                              EditInfoOutputBoundary editInfoPresenter, User user){
        this.editInfoDAO= editInfoDataAccessObject;
        this.editInfoPresenter = editInfoPresenter;
        this.user = user;
    }


    /**
     * Method that calls DAO and prepares output data and presenter depending on 4 cases.
     * CASE 1: All input fields are empty, or only the repeatPassword field is filled -> No changes to User(Success)
     * CASE 2: Passwords don't match -> No changes to User(Failure)
     * CASE 3: Username(and perhaps other attributes) have been changed ->
     *      Username is already taken(Failure)
     *      Username isn't taken, User is changed(Success)
     * CASE 4: Username unchanged, but other attributes have been changed. -> User is changed(Success)
     * @param input raw input data that contains whatever the user wants to change(eg. new password, new username, etc.)
     * @return a User object with updated username, password, etc.
     */
    public User execute(EditInfoInputData input, UserService userService){

        handleDAO(input, userService);

        if (input.getUserName().isEmpty() && input.getLocation().isEmpty() && input.getPassword().isEmpty()) { // just return current this.user
            EditInfoOutputData editInfoOutputData = new EditInfoOutputData(user, "No changes made. Continue program.", false);
            editInfoPresenter.prepareSuccessView(editInfoOutputData);
        }
        else if (!input.getPassword().equals(input.getRepeatPassword())) { // prepare presenter fail view
            editInfoPresenter.prepareFailView("Passwords don't match.");
        }
        else if (!user.getUserName().equals(input.getUserName())) { // Call DAO.editUsername
            boolean infoChanged = editInfoDAO.editUsername(input.getUserName(), input.getPassword(), input.getLocation(), "./src/main/resources/UserDatabase.csv", "./src/main/resources/DayplanDatabase.csv");
            if (infoChanged) {
                user.setUserName(input.getUserName());
                user.setPassword(input.getPassword());
                user.setLocation(locationToArrayList(input, input.getLocation()));

                EditInfoOutputData editInfoOutputData = new EditInfoOutputData(user, "Successfully updated info.", false);
                editInfoPresenter.prepareSuccessView(editInfoOutputData);
            }else{
                editInfoPresenter.prepareFailView("Username is already taken.");
            }
        }
        else{
            // PROCESS USERNAME... set empty string "" to current existing username, etc.
            // Process password, etc. any unchanged fields.
            ArrayList<String> newUserInfo = processInput(input);
            editInfoDAO.editPasswordOrLocation(newUserInfo.get(1), newUserInfo.get(2), "./src/main/resources/UserDatabase.csv", "./src/main/resources/DayplanDatabase.csv");
            user.setPassword(input.getPassword());
            user.setLocation(locationToArrayList(input, input.getLocation()));
            EditInfoOutputData editInfoOutputData = new EditInfoOutputData(user, "Successfully updated info.", false);
            editInfoPresenter.prepareSuccessView(editInfoOutputData);
        }
        return user;
    }


    /**
     * If input data has any fields left empty, reassign to the pre-existing "default" attributes of the User.
     * For example, if location is left empty, reassign to be equal to the previous location value.
     * Helper method used in CASE 4 of .execute() method.
     * @param input raw input data.
     * @return ArrayList of strings, with each index representing username, password, and location respectively.
     */
    private ArrayList<String> processInput(EditInfoInputData input){
        // If a string is empty, set it to the current user attributes
        ArrayList<String> newUserInfo = new ArrayList<String>();
        newUserInfo.add(input.getUserName());
        newUserInfo.add(input.getPassword());
        newUserInfo.add(input.getLocation());
        if (input.getUserName().isEmpty()){newUserInfo.set(0, user.getUserName());}    // setting current username to old username
        if (input.getPassword().isEmpty()){newUserInfo.set(1, user.getUserName());}
        if (input.getLocation().isEmpty()){newUserInfo.set(2, input.removeLocationSpaces(user.getLocation().toString()));}
        return newUserInfo;
    }


    /**
     * Helper method that converts a given String location into an ArrayList of Doubles.
     * Helper method used in CASE 3 and CASE 4 of .execute() method.
     * @param input raw input data.
     * @param location given in form of String.
     * @return a location in form of an ArrayList of Doubles.
     */
    private ArrayList<Double> locationToArrayList(EditInfoInputData input, String location){
        ArrayList<Double> doubLocation = new ArrayList<>();
        location = input.removeLocationSpaces(location);
        ArrayList<String> stringLocation= new ArrayList<String>((List.of(location.split(","))));
        for (String s : stringLocation) {
            doubLocation.add(Double.valueOf(s));
        }
        return doubLocation;
    }

    /**
     * Helper method that gives DAO the necessary input info to set attributes.
     * @param input contains an input object that contains updated username, password, location
     * @param userService contains old User object that we need to compare new input to.
     */
    private void handleDAO(EditInfoInputData input, UserService userService){
        editInfoDAO.setCurrUserAndChanges(userService.getCurrentUser(), input.getUserName(), input.getPassword(), input.getLocation());
    }

}
