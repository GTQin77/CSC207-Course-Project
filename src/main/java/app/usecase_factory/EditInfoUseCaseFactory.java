package app.usecase_factory;

import data_access.EditInfoDataAccessObject;
import interface_adapter.EditInfo.*;
import interface_adapter.ViewManagerModel;
//import interface_adapter.Welcome.WelcomeViewModel;
import services.UserService;
import use_case.edit_info.EditInfoInputBoundary;
import use_case.edit_info.EditInfoInteractor;
import use_case.edit_info.EditInfoOutputBoundary;
import view.EditInfoView;

import javax.swing.*;
import java.io.IOException;


public class EditInfoUseCaseFactory {

    /** Prevent instantiation. */
    private EditInfoUseCaseFactory() {}

    public static EditInfoView create(ViewManagerModel viewManagerModel, EditInfoViewModel editInfoViewModel, UserService userService) {

        try {
            EditInfoController editInfoController = createEditInfoUseCase(viewManagerModel, editInfoViewModel, userService);
            return new EditInfoView(editInfoController, editInfoViewModel, viewManagerModel, userService);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error.");
        }
        return null;
    }

    private static EditInfoController createEditInfoUseCase(ViewManagerModel viewManagerModel, EditInfoViewModel editInfoViewModel, UserService userService) throws IOException {
        EditInfoDataAccessObject editInfoDAO = new EditInfoDataAccessObject();

        editInfoDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        EditInfoOutputBoundary editInfoOutputBoundary = new EditInfoPresenter(viewManagerModel, editInfoViewModel);

        EditInfoInputBoundary editInfoInteractor = new EditInfoInteractor(
                editInfoDAO, editInfoOutputBoundary, userService.getCurrentUser());

        return new EditInfoController(userService, editInfoInteractor);
    }
}
