package interface_adapter.Dayplan;

import entity.Business;
import interface_adapter.BusinessDetails.BusinessDetailsViewModel;
import interface_adapter.ViewManagerModel;
import services.UserService;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.dayplanList.UserDayPlanOutputData;
import use_case.refresh.RefreshOutputBoundary;
import use_case.refresh.RefreshOutputData;
import view.BusinessDetailsViewInterface;
import view.ViewManager;

public class DayplanPresenter implements  UserDayPlanOutputBoundary, RefreshOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private UserService userService;


    public DayplanPresenter(ViewManagerModel viewManagerModel, UserService userService) {
        this.viewManagerModel = viewManagerModel;
        this.userService = userService;
    }

    /**
     * Navigates to the Dayplan Input view, updating the previous view history and current active view.
     */
    public void navigateToDayplanInput() {
        userService.setPrevView("DayplanView");
        viewManagerModel.setActiveView("DayplanInput");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the Business Details view, storing the selected business in the user service
     * for subsequent operations and updating the current active view.
     *
     * @param business the business entity whose details are to be displayed.
     */
    public void navigateToBusinessDetails(Business business) {
        userService.setBusiness(business);
        viewManagerModel.setActiveView("BusinessDetails");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the Edit User Information view, updating the previous view history and current active view.
     */
    public void navigateToEditUser() {
        userService.setPrevView("DayplanView");
        viewManagerModel.setActiveView("EditUserInfo");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareDayplanView(UserDayPlanOutputData data) {

    }

    @Override
    public void prepareAllSuccessView(RefreshOutputData refresh) {

    }
}
