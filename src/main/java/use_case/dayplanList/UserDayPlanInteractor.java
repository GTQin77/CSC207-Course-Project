package use_case.dayplanList;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessInterface;
import entity.Dayplan;
import entity.DayplanFactory;
import entity.BusinessFactory;
import entity.UserFactory;
import use_case.user.UserSignupOutputBoundary;
// NOTE TO SELF: Should import a DayplanFactory interface instead, no time :(

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDayPlanInteractor implements UserDayPlanInputBoundary{
    final DayPlanDataAccessInterface dayPlanDataAccessObject;
    final UserDayPlanOutputBoundary dayplanPresenter;
    final DayplanFactory dayplanFactory;

    public UserDayPlanInteractor(DayPlanDataAccessInterface dayPlanDataAccessInterface,
                                UserDayPlanOutputBoundary userDayPlanOutputBoundary,
                                DayplanFactory dayplanFactory) {
        this.dayPlanDataAccessObject = dayPlanDataAccessInterface;
        this.dayplanPresenter = userDayPlanOutputBoundary;
        this.dayplanFactory = dayplanFactory;
    }

    @Override
    public void execute(UserDayPlanInputData input) {
        // 1. Process Input Data
        ArrayList<Double> location = new ArrayList<Double>();
        for (int i = 0; i < input.getLocation().size(); i++){
            Double coord = Double.valueOf(input.getLocation().get(i));
            location.add(coord);
        }
        // 2. Create a new Dayplan object using factories
        Dayplan dayplan = this.dayplanFactory.create(input.getUser(), location, input.getCity(), input.getNumMeals(),
                input.getNumActivities(), input.getDescription());
        // 3. Write new Dayplan to database using DAO
        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        // 4. Prepare Output Data
        UserDayPlanOutputData outputData = new UserDayPlanOutputData(dayplan);
        this.dayplanPresenter.prepareDayplanView(outputData);
    }
}
