package use_case.user_signup;

import entity.User;

public class UserSignupOutputData {

    private final User user;
    private String creationTime;

    private boolean useCaseFailed;

    /**
     * Output data of the user sign up use case.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for SignupOutputData on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupOutputData.java">github.com</a>.
     * </p>
     *
     * @param user User object that was created, for use in future use cases.
     * @param creationTime Time of the account being created.
     * @param useCaseFailed Whether the account creation is successful.
     */
    public UserSignupOutputData(User user, String creationTime, boolean useCaseFailed) {
        this.user = user;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}