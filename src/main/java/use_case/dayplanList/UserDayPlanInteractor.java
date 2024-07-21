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
        // NOTE TO SELF: Should ideally call a PlanFactory interface which calls a DayPlanFactory
        // TO MYSELF, AMELIA: CONTINUE HERE!



    }

}
