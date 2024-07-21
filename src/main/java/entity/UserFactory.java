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
     * <p>
     * This implementation referenced the Paulgries' Clean Architecture code for UserFactory on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/entity/UserFactory.java">github.com</a>.
     * </p>
     * @param userName the name of the user
     * @param password the password associated with user account
     * @param location the latitude and longitude specified by user.
     * @return a new User instance
     */
    User create(String userName, String password, ArrayList<Double> location);
}
