package interface_adapter.Dayplan;

import interface_adapter.ViewManagerModel;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.dayplanList.UserDayPlanOutputData;
import view.ViewManager;

public class DayplanPresenter {
    private ViewManagerModel viewManagerModel;

    public DayplanPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void navigateToDayplanInput() {
        viewManagerModel.setActiveView("DayplanInput");
        viewManagerModel.firePropertyChanged();
    }

    public void navigateToEditUser() {
        viewManagerModel.setActiveView("EditUserInfo");
        viewManagerModel.firePropertyChanged();
    }
}
