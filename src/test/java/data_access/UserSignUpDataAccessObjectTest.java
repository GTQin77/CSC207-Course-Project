package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserSignUpDataAccessObjectTest {

    private UserSignupDataAccessObject userSignUpDAOTest;

    @BeforeEach
    void setUp() {
        userSignUpDAOTest = new UserSignupDataAccessObject();
        userSignUpDAOTest.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
    }

    @Test
    void setcsvPathAndcsvFile(){



    }







}
