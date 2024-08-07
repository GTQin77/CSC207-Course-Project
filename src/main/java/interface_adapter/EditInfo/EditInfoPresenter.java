package interface_adapter.EditInfo;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.edit_info.EditInfoOutputData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EditInfoPresenter implements EditInfoOutputBoundary {
    private final EditInfoViewModel editInfoViewModel;
    private EditInfoViewManagerModel editInfoViewManagerModel;

    public EditInfoPresenter(EditInfoViewManagerModel viewManagerModel,
                           EditInfoViewModel editInfoViewModel) {
        this.editInfoViewModel = editInfoViewModel;
        this.editInfoViewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(EditInfoOutputData output) {
        EditInfoState editInfoState = editInfoViewModel.getState();

        editInfoState.setUsername(output.getUser().getUserName());
        this.editInfoViewModel.setState(editInfoState);
        editInfoViewModel.firePropertyChanged();
        editInfoViewManagerModel.setActiveView(editInfoViewModel.getViewName());
        editInfoViewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        EditInfoState editInfoState = editInfoViewModel.getState();
        editInfoState.setUsernameError(errorMessage);
        editInfoViewModel.firePropertyChanged();
    }
}
