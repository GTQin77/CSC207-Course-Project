package use_case.user_login;

/**
 * Output boundary of the user login use case.
 */
public interface UserLoginOutputBoundary {
    void presentLoginResult(UserLoginOutputData outputData);
}
