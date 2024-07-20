package use_case.user;


public class UserSignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;


    /**
     * Input data of the user sign up use case.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for SignupInputData on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">github.com</a>.
     * </p>
     *
     * @param username Username of the account.
     * @param password Password of the account.
     * @param repeatPassword Repeated password of the account.
     */
    public UserSignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}