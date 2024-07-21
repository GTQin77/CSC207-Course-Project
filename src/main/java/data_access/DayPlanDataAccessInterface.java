package data_access;

import entity.User;
import entity.Dayplan;

public interface DayPlanDataAccessInterface {

    void saveDayPlan(User user, Dayplan dayplan);
}
