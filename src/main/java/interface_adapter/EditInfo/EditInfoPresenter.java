package interface_adapter.EditInfo;

import interface_adapter.ViewManagerModel;
import use_case.edit_info.EditInfoOutputBoundary;
import use_case.edit_info.EditInfoOutputData;



public class EditInfoPresenter implements EditInfoOutputBoundary {
    private final EditInfoViewModel editInfoViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditInfoPresenter(ViewManagerModel viewManagerModel,
                           EditInfoViewModel editInfoViewModel) {
        this.editInfoViewModel = editInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    /**
     * Prepares the view for a successful edit operation.
     *
     * @param output The data from the successful edit, containing the updated user information.
     */
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

    /**
     * Prepares the view for a failed edit operation.
     *
     * @param errorMessage The error message describing why the edit failed.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        EditInfoState editInfoState = editInfoViewModel.getState();
        editInfoState.setUsernameError(errorMessage);
        this.editInfoViewModel.setState(editInfoState);
        editInfoViewModel.firePropertyChanged();
    }
}
