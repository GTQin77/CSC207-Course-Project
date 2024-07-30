package use_case.user_login;

import entity.User;

/**
 * Implementation design taken from Paul Gries LoginCleanArchitecture
 * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/app/SignupUseCaseFactory.java">github</a>
 * Input boundary of the user login use case.
 */
public interface UserLoginInputBoundary {
    User execute(UserLoginInputData loginInputData);
}

