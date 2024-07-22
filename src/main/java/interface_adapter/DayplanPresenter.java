package interface_adapter;

import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.dayplanList.UserDayPlanOutputData;
import entity.Dayplan;

public class DayplanPresenter implements UserDayPlanOutputBoundary {

    private final DayplanViewModel dayplanViewModel;

    public DayplanPresenter(DayplanViewModel dayplanViewModel) {
        this.dayplanViewModel = dayplanViewModel;
    }

    @Override
    public void prepareDayplanView(UserDayPlanOutputData data) {
        String dayplan = data.getDayplan().dayplanToString();
        System.out.println(dayplan);
    }
}
