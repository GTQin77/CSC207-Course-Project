package data_access;

import entity.Dayplan;
import entity.User;

public interface PrevPlanDataAccessInterface {
    String getPreviousDayplan(User user);
}
