package interface_adapter;

import java.util.Scanner;

public class SignupViewModel extends ViewModel{
    private String userName;
    private String password;
    private String repeatedPassword;

    public SignupViewModel() {
        super("SignupViewModel");
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }
    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }


    public void getUserConsoleInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        this.userName = scanner.nextLine();
        System.out.println("Enter your password: ");
        this.password = scanner.nextLine();
        System.out.println("Enter repeated password: ");
        this.repeatedPassword = scanner.nextLine();

        if (!this.password.equals(this.repeatedPassword)) {
            System.out.println("Passwords do not match, signup failed.");
            return;
        }

    }

}
