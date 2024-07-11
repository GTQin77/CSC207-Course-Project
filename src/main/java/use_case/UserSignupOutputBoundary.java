package use_case;

public interface UserSignupOutputBoundary {
    void prepareSuccessView(UserSignupOutputData user);

    void prepareFailView(String error);
}