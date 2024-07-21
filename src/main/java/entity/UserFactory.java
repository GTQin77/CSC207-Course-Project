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
     * This implementation referenced the Pualgries' Clean Architecture code for UserFactory on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/entity/UserFactory.java">github.com</a>.
     * </p>
     * @param userName the name of the user
     * @param location the location coordinates of the user
     * @return a new User instance
     */
    User create(String userName, ArrayList<Double> location);
}
