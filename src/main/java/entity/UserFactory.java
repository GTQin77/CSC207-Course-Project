package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * Factory interface for creating User instances.
 */
public interface UserFactory {
    //taken from Paul Gries Login Clean Architecture
    /**
     * Creates a new User instance with the specified attributes.
     *
     * @param userID the unique ID of the user
     * @param userName the name of the user
     * @param location the location coordinates of the user
     * @param mood the current mood of the user
     * @param numActivities the number of activities the user has participated in
     * @param numMeals the number of meals the user has had
     * @return a new User instance
     */
    User create(int userID, String userName, ArrayList<Float> location, String mood,
                int numActivities, int numMeals);
}
