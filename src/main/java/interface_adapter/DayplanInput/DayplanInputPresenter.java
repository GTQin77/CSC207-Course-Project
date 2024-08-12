package interface_adapter.DayplanInput;

import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.ViewManagerModel;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.dayplanList.UserDayPlanOutputData;

public class DayplanInputPresenter implements UserDayPlanOutputBoundary {
    private final DayplanInputViewModel dayplanInputViewModel;
    private final DayplanViewModel dayplanViewModel;
    private ViewManagerModel viewManagerModel;

    public DayplanInputPresenter(ViewManagerModel viewManagerModel, DayplanInputViewModel dayplanInputViewModel, DayplanViewModel dayplanViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dayplanInputViewModel = dayplanInputViewModel;
        this.dayplanViewModel = dayplanViewModel;
    }

    /**
     * This switch to dayplan view.
     * @param data response from the UserDayPlanOutputData.
     */
    @Override
    public void prepareDayplanView(UserDayPlanOutputData data) {
        viewManagerModel.setActiveView(dayplanViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
