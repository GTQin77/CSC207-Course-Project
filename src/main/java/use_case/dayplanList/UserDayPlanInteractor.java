package use_case.dayplanList;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessInterface;
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
    public void execute(UserDayPlanInputData userDayPlanInputData) {
        // 1. Create a new Dayplan object using factories
        // 2. Write new Dayplan to database using DAO
        // 3. Convert Dayplan to String



    }

}
