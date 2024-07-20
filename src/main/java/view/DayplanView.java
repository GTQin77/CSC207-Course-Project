package view;

import interface_adapter.DayplanController;
import interface_adapter.DayplanViewModel;

import java.util.Scanner;

public class DayplanView {

    private final DayplanController dayplanController;
    private final DayplanViewModel dayplanViewModel;


    public DayplanView(DayplanController dayplanController, DayplanViewModel dayplanViewModel) {
        this.dayplanController = dayplanController;
        this.dayplanViewModel = dayplanViewModel;

        //add code here that takes whatever userInput and creates a new object out of it
        String prompt = dayplanViewModel.getPrompt();
        System.out.println(prompt);
        dayplanViewModel.setUserInput();
        String userInput = dayplanViewModel.getUserInput();
        dayplanController.execute(userInput);
    }
}
