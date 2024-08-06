package use_case.previous_plan;

import entity.PreviousPlan;

public interface PreviousPlanInputBoundary {
    PreviousPlan execute(PreviousPlanInputData previousPlanInputData);
}
