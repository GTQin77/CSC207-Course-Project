package interface_adapter;

import java.util.Scanner;

public class SignupViewModel extends ViewModel {
    private String userName;
    private String password;
    private String repeatedPassword;

    public SignupViewModel() {
        super("SignupViewModel");
    }

    public void setUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        this.userName = scanner.nextLine();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password: ");
        this.password = scanner.nextLine();
    }


    public String getPassword() {
        return this.password;
    }


    public void setRepeatedPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter repeated password: ");
        this.repeatedPassword = scanner.nextLine();
    }
    public String getRepeatedPassword() {
        return this.repeatedPassword;
    }

}

