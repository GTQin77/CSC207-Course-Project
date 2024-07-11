package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(int userID, String userName, ArrayList<Float> location, String mood,
                int numActivities, int numMeals);
}
