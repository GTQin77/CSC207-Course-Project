package app;

import data_access.EditInfoDataAccessObject;
import data_access.EditInfoDataAccessInterface;
import data_access.UserSignupDataAccessObject;
import entity.*;
import interface_adapter.EditInfo.*;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Welcome.WelcomeViewModel;
import use_case.edit_info.EditInfoInputBoundary;
import use_case.edit_info.EditInfoInteractor;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.user_signup.UserSignupInputBoundary;
import use_case.user_signup.UserSignupInteractor;
import use_case.user_signup.UserSignupOutputBoundary;
import view.SignupView;
import view.EditInfoView;

import javax.swing.*;
import java.io.IOException;


public class EditInfoUseCaseFactory {

    /** Prevent instantiation. */
    private EditInfoUseCaseFactory() {}

    public static EditInfoView create(EditInfoViewManagerModel viewManagerModel, EditInfoViewModel editInfoViewModel) {

        try {
            EditInfoController editInfoController = createEditInfoUseCase(viewManagerModel, editInfoViewModel);
            return new EditInfoView(editInfoController, editInfoViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error.");
        }
        return null;
    }

    private static EditInfoController createEditInfoUseCase(EditInfoViewManagerModel editInfoViewManagerModel, EditInfoViewModel editInfoViewModel) throws IOException {
        // DAO takes in a user object + user's input... but i have no idea how to get that :((
        EditInfoDataAccessObject editInfoDAO = new EditInfoDataAccessObject();


        editInfoDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        // Notice how we pass this method's parameters to the Presenter.
        EditInfoOutputBoundary editInfoOutputBoundary = new EditInfoPresenter(editInfoViewManagerModel, editInfoViewModel);

        EditInfoInputBoundary editInfoInteractor = new EditInfoInteractor(
                editInfoDAO, editInfoOutputBoundary, user); // Need to input an actual user object here

        return new EditInfoController(editInfoInteractor);
    }





}
