package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(int userID, String userName, ArrayList<Float> location, String mood,
                       int numActivities, int numMeals) {
        return new User(userID, userName, location, mood, numActivities, numMeals);
    }
}
