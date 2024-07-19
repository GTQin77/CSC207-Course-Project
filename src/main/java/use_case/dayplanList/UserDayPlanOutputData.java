package use_case.dayplanList;

import entity.Dayplan;

public class UserDayPlanOutputData {
    private final Dayplan dayplan;

    public UserDayPlanOutputData(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public Dayplan getDayplan() {
        
        return dayplan;
    }
}
