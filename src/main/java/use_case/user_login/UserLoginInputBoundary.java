package use_case.user_login;

import entity.User;

/**
 * Input boundary of the user login use case.
 */
public interface UserLoginInputBoundary {
    User execute(UserLoginInputData loginInputData);
}

