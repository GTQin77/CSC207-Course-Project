package use_case.user;

public interface UserSignupOutputBoundary {
    void prepareSuccessView(UserSignupOutputData user);

    void prepareFailView(String error);
}