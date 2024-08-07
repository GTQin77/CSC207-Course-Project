package interface_adapter.Dayplan;

import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.dayplanList.UserDayPlanOutputData;

public class DayplanPresenter implements UserDayPlanOutputBoundary {

    private final DayplanViewModel dayplanViewModel;

    public DayplanPresenter(DayplanViewModel dayplanViewModel) {
        this.dayplanViewModel = dayplanViewModel;
    }

    // this is where the dayplan gets the input data from the other s
    @Override
    public void prepareDayplanView(UserDayPlanOutputData data) {
        String dayplan = data.getDayplan().dayplanToString();
        System.out.println(dayplan);
    }
}
