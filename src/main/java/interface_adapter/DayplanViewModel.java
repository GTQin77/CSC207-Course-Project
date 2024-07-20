package interface_adapter;

import java.util.Scanner;

public class DayplanViewModel extends ViewModel {
    public final String titleLabel = "Prompt Input";
    public final String prompt = "Describe how your day is in a sentence:";
    public String userInput;

    public DayplanViewModel() {
        super("Dayplan VM");
    }

    public String getTitleLabel() {
        return this.titleLabel;
    }

    public String getPrompt() {
        return this.prompt;
    }

    /**
     * Gets String userInput from DayplanViewModel
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
