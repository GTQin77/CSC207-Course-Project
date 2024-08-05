package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class CommonUserFactoryTest {
    private CommonUserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();
    }


    @Test
    void create() {
        String userName = "testUser";
        String password = "testPassword";
        ArrayList<Double> location = new ArrayList<>();
        location.add(22.3344);
        location.add(-22.3344);

        User user = userFactory.create(userName, password, location);

        assertNotNull(user);
        assertEquals(userName, user.getUserName());
        assertEquals(password, user.getPassword());
        assertEquals(location, user.getLocation());
    }
}