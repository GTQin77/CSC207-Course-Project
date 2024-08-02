package interface_adapter.Login;

import interface_adapter.ViewModel;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginViewModel extends ViewModel {
    private String userName;
    private String password;
    private ArrayList<Double> location;

    public LoginViewModel() {
        super("LoginViewModel");
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
}
