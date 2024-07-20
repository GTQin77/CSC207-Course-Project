package use_case.dayplanList;

import entity.Dayplan;
import entity.User;

public class UserDayPlanInputData {


    // commented out code for now, to get my stuff done - josh
//    final private User user;
    final private String userInput;

    public UserDayPlanInputData(String userInput) {
//        this.user = user;
        this.userInput = userInput;
    }

//    public User getUser() {
//        return this.user;
//    }

    public String getUserInput() {
        return this.userInput;
    }

}
