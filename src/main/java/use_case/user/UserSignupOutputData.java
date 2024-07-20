package use_case.user;

public class UserSignupOutputData {

    private final String username;
    private String creationTime;

    private boolean useCaseFailed;

    /**
     * Output data of the user sign up use case.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for SignupOutputData on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupOutputData.java">github.com</a>.
     * </p>
     *
     * @param username Username of the account.
     * @param creationTime Time of the account being created.
     * @param useCaseFailed Whether the account creation is successful.
     */
    public UserSignupOutputData(String username, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}