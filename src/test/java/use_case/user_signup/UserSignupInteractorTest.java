package use_case.user_signup;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.Login.UserLoginPresenter;
import interface_adapter.Signup.SignupPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.dayplanList.UserDayPlanInputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class UserSignupInteractorTest {
    private UserFactory userFactory;
    private UserSignupOutputBoundary signupOutputBoundary;
    private UserDayPlanInputBoundary userDayPlanInputData;

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();

    }

    @Test
    void execute() {
    }
}