package use_case.refresh;

import entity.Dayplan;

/**
 * Interface defining the input boundary for refreshing day plan.
 */
public interface RefreshInputBoundary {
    Dayplan execute(RefreshInputData refreshInputData);
}
