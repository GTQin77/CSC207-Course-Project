package interface_adapter.EditInfo;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.edit_info.EditInfoOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EditInfoPresenter implements EditInfoOutputBoundary {
    private final EditInfoViewModel editInfoViewModel;
    private ViewManagerModel viewManagerModel;

    public EditInfoPresenter(ViewManagerModel viewManagerModel,
                           EditInfoViewModel editInfoViewModel) {
        this.editInfoViewModel = editInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(EditInfoOutputData output) {
        EditInfoState editInfoState = editInfoViewModel.getState();

        editInfoState.setUsername(output.getUser().getUserName());
        editInfoState.setUsernameError(null);
        this.editInfoViewModel.setState(editInfoState);
        editInfoViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        EditInfoState editInfoState = editInfoViewModel.getState();
        editInfoState.setUsernameError(errorMessage);
        this.editInfoViewModel.setState(editInfoState);
        editInfoViewModel.firePropertyChanged();
    }
}
