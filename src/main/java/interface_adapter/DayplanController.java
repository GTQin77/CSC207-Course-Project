package interface_adapter;

import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

public class DayplanController {

    final UserDayPlanInputBoundary userDayPlanUseCaseInteractor;

    public DayplanController(UserDayPlanInputBoundary userDayPlanUseCaseInteractor) {
        this.userDayPlanUseCaseInteractor = userDayPlanUseCaseInteractor;
    }

    public void execute(String username, String location, String city, int numMeals, int numActivities, String description) {
        UserDayPlanInputData inputData = new UserDayPlanInputData(username, location, city, numMeals, numActivities, description);

        userDayPlanUseCaseInteractor.execute(inputData);
    }

}
