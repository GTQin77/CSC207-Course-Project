package interface_adapter;

import entity.User;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

public class DayplanController {

    final UserDayPlanInputBoundary userDayPlanUseCaseInteractor;

    public DayplanController(UserDayPlanInputBoundary userDayPlanUseCaseInteractor) {
        this.userDayPlanUseCaseInteractor = userDayPlanUseCaseInteractor;
    }

    public void execute(User user, String location, String city, int numMeals, int numActivities, String description) {
        UserDayPlanInputData inputData = new UserDayPlanInputData(user, location, city, numMeals, numActivities, description);

        userDayPlanUseCaseInteractor.execute(inputData);
    }

}
