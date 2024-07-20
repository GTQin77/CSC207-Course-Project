package view;

import java.util.Scanner;

public class DayplanView {

    private String userInput;

    /**
     * Gets String userInput from DayplanView
     * */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Get userInput from the console
     * */
    public void setUserInput() {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        this.userInput = input.nextLine();
    }
}
