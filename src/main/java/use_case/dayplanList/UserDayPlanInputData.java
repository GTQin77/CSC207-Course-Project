package use_case.dayplanList;

import entity.Dayplan;
import entity.User;

public class UserDayPlanInputData {
//    final private String vibe;
//    final private Dayplan dayplan;
    final private User user;

    public UserDayPlanInputData(User user) {
//        this.vibe = vibe;
//        this.dayplan = dayplan;
        this.user = user;
    }

//    public Dayplan getDayplan() {
//        return dayplan;
//    }

    public User getUser() {
        return user;
    }

}
