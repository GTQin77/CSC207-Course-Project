package use_case.previous_plan;

import entity.User;

public class PreviousPlanInputData {
    // input data should be just User?
    private User user;

    public PreviousPlanInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
