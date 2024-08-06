package interface_adapter.Dayplan;

import java.util.Scanner;
import entity.User;
import interface_adapter.ViewModel;

public class DayplanViewModel extends ViewModel {
    public final String TITLE_LABEL = "Dayplan View";
    public final String


    public DayplanViewModel() {
        super("Dayplan VM");
    }


    public String getUserDescription() {
        return this.userDescription;
    }

    public void setUserDescription() {
        System.out.println(this.prompt);
        Scanner input = new Scanner(System.in);
        this.userDescription = input.nextLine();
    }

    /**
     * Get userCity from the console
     * Precondition: user input must be in the full name of the city
     * */
    public void setUserCity() {
        System.out.println("Please enter the full name of the city you are located in:");
        Scanner input = new Scanner(System.in);
        this.userCity = input.nextLine();
    }

    public String getUserCity() {
        return this.userCity;
    }

    public void setUserNumMeals() {
        System.out.println("Please enter the desired number of meals today:");
        Scanner input = new Scanner(System.in);
        this.userNumMeals = input.nextByte();
    }

    public int getUserNumMeals() {
        return this.userNumMeals;
    }

    public void setUserNumActivities() {
        System.out.println("Please enter the desired number of activities today:");
        Scanner input = new Scanner(System.in);
        this.userNumActivities = input.nextByte();
    }

    public int getUserNumActivities() {
        return this.userNumActivities;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
