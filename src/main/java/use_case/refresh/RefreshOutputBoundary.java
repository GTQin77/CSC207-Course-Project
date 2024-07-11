package use_case.refresh;

public interface RefreshOutputBoundary {
    void prepareAllSuccessView(RefreshOutputData refresh); // for refreshing all events

    void prepareOneSuccessView(RefreshOutputData refresh); // for refreshing one event

    // question: do we need a refresh failed view?

}
