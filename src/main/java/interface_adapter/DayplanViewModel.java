package interface_adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DayplanViewModel extends ViewModel {
    public final String titleLabel = "Prompt Input";
    public final String prompt = "Describe how your day is in a sentence:";
    public String userInput;


    public DayplanViewModel() {
        super("Dayplan VM");
    }

    /**
     * Gets String userPrompt from DayplanViewModel
     * */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Set the userInput from console
     * */
    public void setUserInput() {
        try {
            BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));
            this.userInput = inputData.readLine();
        } catch (IOException reader) {
            System.out.println("Something went wrong!");
        }
    }
}
