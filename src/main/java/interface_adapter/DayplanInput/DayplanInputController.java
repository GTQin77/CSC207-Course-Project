package interface_adapter.DayplanInput;

import entity.Dayplan;
import entity.User;
import services.UserService;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

import java.util.ArrayList;

public class DayplanInputController {
    private final UserService userService;
    final UserDayPlanInputBoundary userDayPlanInteractor;

    public DayplanInputController(UserService userService, UserDayPlanInputBoundary userDayPlanInteractor) {
        this.userService = userService;
        this.userDayPlanInteractor = userDayPlanInteractor;
    }

    /**
     * Executes the operation to create a dayplan based on user input.
     *
     * @param city The city of the user.
     * @param numMeals The number of meals planned for the day.
     * @param numActivities The number of activities planned for the day.
     * @param description A description of what the user want the day to be like.
     */
    public void execute(String city, int numMeals, int numActivities, String description){
        User user = userService.getCurrentUser();

        ArrayList<Double> coordinates = user.getLocation();
        String location = coordinates.getFirst() + "," + coordinates.getLast();

        UserDayPlanInputData userDayPlanInputData = new UserDayPlanInputData(
                user, location, city, numMeals, numActivities, description);

        Dayplan dayplan = userDayPlanInteractor.execute(userDayPlanInputData);
        userService.setDayplan(dayplan);
    }

}
