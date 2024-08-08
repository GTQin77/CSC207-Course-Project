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


    public DayplanPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void navigateToDayplanInput() {
        userService.setPrevView("DayplanView");
        viewManagerModel.setActiveView("DayplanInput");
        viewManagerModel.firePropertyChanged();
    }

    public void navigateToBusinessDetails(Business business) {
        userService.setBusiness(business);
        viewManagerModel.setActiveView("BusinessDetails");
        viewManagerModel.firePropertyChanged();
    }


    public void navigateToEditUser() {
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
