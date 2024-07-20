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
}
