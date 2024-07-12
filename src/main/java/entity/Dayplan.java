package entity;

import com.theokanning.openai.billing.Plan;

import java.util.ArrayList;

public class Dayplan {
    private String vibe;
    private User user;
    private ArrayList<Events> plan;

    public Dayplan() {
        plan = new ArrayList<>();
    }
    public String getVibe() {
        return vibe;
    }
    public void setVibe(String vibe) {
        this.vibe = vibe;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<Events> getPlan() {
        return plan;

    }
}