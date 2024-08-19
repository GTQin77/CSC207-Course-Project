package use_case.refresh;

/**
 * Interface defining the output boundary for refreshing day plan.
 */
public interface RefreshOutputBoundary {
    void prepareAllSuccessView(RefreshOutputData refresh); // for refreshing all events
}
