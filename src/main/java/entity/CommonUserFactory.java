package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {
//taken from Paul Gries Login Clean Architecture
    /**
     * Creates a new User instance with the specified attributes.
     * <p>
     * This implementation referenced the Paulgries' Clean Architecture code for CommonUserFactory on
     * <a href="https://github.com/paulgries/LoginCleanArchitecture/blob/main/src/entity/CommonUserFactory.java">github.com</a>.
     * </p>
     * @param userName the name of the user
     * @param password the password associated with the account
     * @param location the location coordinates of the user
     * @return a new User instance
     */
    @Override
    public User create(String userName, String password, ArrayList<Double> location) {
        return new User(userName, password, location);
    }
}
