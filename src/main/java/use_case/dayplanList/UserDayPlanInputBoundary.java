package use_case.dayplanList;

import entity.Dayplan;

public abstract interface UserDayPlanInputBoundary {
    Dayplan execute(UserDayPlanInputData userDayPlanInputData);
}
