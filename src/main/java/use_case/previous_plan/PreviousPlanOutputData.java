package use_case.previous_plan;

import entity.PreviousPlan;

public class PreviousPlanOutputData {

    private final PreviousPlan previousPlan;

    public PreviousPlanOutputData(PreviousPlan previousPlan) {
        this.previousPlan = previousPlan;
    }

    public PreviousPlan getPreviousPlan() {
        return this.previousPlan;
    }
}
