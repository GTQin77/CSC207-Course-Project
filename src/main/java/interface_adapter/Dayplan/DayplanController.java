package interface_adapter.Dayplan;

import entity.User;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

public class DayplanController {

    final UserDayPlanInputBoundary userDayPlanUseCaseInteractor;

    public DayplanController(UserDayPlanInputBoundary userDayPlanUseCaseInteractor) {
        this.userDayPlanUseCaseInteractor = userDayPlanUseCaseInteractor;
    }

    /**
     * Controller method execute that calls on Interactor.
     * @param user a User object.
     * @param location from input
     * @param city from input
     * @param numMeals from input
     * @param numActivities from input
     * @param description from input
     */
    public void execute(User user, String location, String city, int numMeals, int numActivities, String description) {
        UserDayPlanInputData inputData = new UserDayPlanInputData(user, location, city, numMeals, numActivities, description);

        userDayPlanUseCaseInteractor.execute(inputData);
    }

}
