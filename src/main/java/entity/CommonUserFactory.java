package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {
//taken from Paul Gries Login Clean Architecture
    /**
     * Creates a new User instance with the specified attributes.
     * <p>
     * This implementation referenced the Pualgries' Clean Architecture code for CommonUserFactory on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/entity/CommonUserFactory.java">github.com</a>.
     * </p>
     * @param userID the unique ID of the user
     * @param userName the name of the user
     * @param location the location coordinates of the user
     * @param mood the mood the user wants for the day
     * @param numActivities the number of activities the user wants for the day
     * @param numMeals the number of meals the user wants for the day
     * @return a new User instance
     */
    @Override
    public User create(int userID, String userName, ArrayList<Double> location, String mood,
                       int numActivities, int numMeals) {
        return new User(userID, userName, location, mood, numActivities, numMeals);
    }
}
