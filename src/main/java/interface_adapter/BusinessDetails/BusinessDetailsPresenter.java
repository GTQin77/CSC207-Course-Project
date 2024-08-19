package interface_adapter.BusinessDetails;

import entity.Business;
import interface_adapter.ViewManagerModel;
import services.UserService;
import view.BusinessDetailsViewInterface;

public class BusinessDetailsPresenter {
    private ViewManagerModel viewManagerModel;
    private UserService userService;
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;


    public BusinessDetailsPresenter(ViewManagerModel viewManagerModel, BusinessDetailsViewModel viewModel, UserService userService) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.userService = userService;
    }

    /**
     * Navigates to the business details view, updating the current active view in the application.
     */
    public void navigateToBusinessDetails() {
        viewManagerModel.setActiveView("BusinessDetails");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the edit user information view.
     */
    public void navigateToEditUserInfo() {
        userService.setPrevView("BusinessDetails");
        viewManagerModel.setActiveView("EditUserInfo");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the dayplan view.
     */
    public void nagivateToDayplan() {
        viewManagerModel.setActiveView("DayplanView");
        viewManagerModel.firePropertyChanged();
    }

}
