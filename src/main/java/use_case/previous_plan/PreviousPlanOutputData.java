package use_case.previous_plan;

import entity.Dayplan;

public class PreviousPlanOutputData {

    private final Dayplan dayplan;

    public PreviousPlanOutputData(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public Dayplan getPreviousPlan() {
        return this.dayplan;
    }
}
