package use_case.user_login;

import java.util.ArrayList;

public class UserLoginInputData {
    private final String username;
    private final String password;

    /**
     * Input data of the user login use case.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for LoginInputData on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">github.com</a>.
     * </p>
     *
     * @param username Username of this account.
     * @param password Password of this account.
     */

    public UserLoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
