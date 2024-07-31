package use_case.user_signup;


import java.util.ArrayList;
import java.util.List;

public class UserSignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    final private ArrayList<String> location;


    /**
     * Input data of the user sign up use case.
     * <p>
     * This implementation referenced the Paulgries' Clean Architecture code for SignupInputData on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/use_case/SignupInputData.java">github.com</a>.
     * </p>
     *
     * @param username Username of the account.
     * @param password Password of the account.
     * @param repeatPassword Repeated password of the account.
     * @param location String representation of 2 coordinates, separated by a comma.
     */
    public UserSignupInputData(String username, String password, String repeatPassword, String location) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.location = new ArrayList<String>((List.of(location.split(","))));
    }

    public String getUsername() { return this.username;}

    public String getPassword() { return this.password;}

    public String getRepeatPassword() {return this.repeatPassword;}

    public ArrayList<String> getLocation() {return this.location;}
}