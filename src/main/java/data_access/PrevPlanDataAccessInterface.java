package data_access;

import entity.Dayplan;
import entity.User;

public interface PrevPlanDataAccessInterface {
    Dayplan getPreviousDayplan();

    void getPreviousDayplan(User user);
}
