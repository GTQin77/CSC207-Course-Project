package use_case.dayplanList;

import entity.Dayplan;
import entity.User;

public class UserDayPlanInputData {
    final private User user;

    public UserDayPlanInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
