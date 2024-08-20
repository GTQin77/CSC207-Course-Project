package use_case.edit_info;

import data_access.EditInfoDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;
import services.UserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import java.util.Arrays;

class EditInfoInteractorTest {

    @Mock
    private EditInfoDataAccessInterface editInfoDAO;
    @Mock
    private EditInfoOutputBoundary editInfoPresenter;
    @Mock
    private UserService userService;

    private EditInfoInteractor interactor;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User("currentUsername", "currentPassword", new ArrayList<>(Arrays.asList(40.7128, -74.0060)));
        interactor = new EditInfoInteractor(editInfoDAO, editInfoPresenter, user);
        when(userService.getCurrentUser()).thenReturn(user);
    }

    @Test
    void testSuccessfulNoChanges() {
        EditInfoInputData inputData = new EditInfoInputData("currentUsername", "currentPassword", "currentPassword", "40.7128,-74.0060", user);

        User result = interactor.execute(inputData, userService);

        assertEquals(user, result);
        verify(editInfoPresenter).prepareSuccessView(any());
    }

    @Test
    void testPasswordMismatch() {
        EditInfoInputData inputData = new EditInfoInputData("currentUsername", "newPassword", "anotherPassword", "40.7128,-74.0060", user);

        User result = interactor.execute(inputData, userService);

        assertNotNull(result);
        verify(editInfoPresenter).prepareFailView("Passwords don't match.");
    }

    @Test
    void testExistingUser() {
        when(editInfoDAO.editUsername(any(), any(), any(), anyString(), anyString())).thenReturn(false);
        EditInfoInputData inputData = new EditInfoInputData("newUsername", "newPassword", "newPassword", "40.7128,-74.0060", user);

        User result = interactor.execute(inputData, userService);

        assertNotNull(result);
        verify(editInfoPresenter).prepareFailView("Username is already taken.");
    }

    @Test
    void testUsernameChangeSuccess() {
        when(editInfoDAO.editUsername(any(), any(), any(), anyString(), anyString())).thenReturn(true);
        EditInfoInputData inputData = new EditInfoInputData("newUsername", "newPassword", "newPassword", "40.7128,-74.0060", user);

        User result = interactor.execute(inputData, userService);

        assertNotNull(result);
        assertEquals("newUsername", result.getUserName());
        verify(editInfoPresenter).prepareSuccessView(any());
    }

    @Test
    void testAttributeChangeSuccess() {
        EditInfoInputData inputData = new EditInfoInputData("currentUsername", "newPassword", "newPassword", "40.7128,-74.0060", user);

        when(editInfoDAO.editUsername(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(false);
        interactor.execute(inputData, userService);

        verify(editInfoDAO).editPasswordOrLocation("newPassword", "40.7128,-74.0060", "./src/main/resources/UserDatabase.csv", "./src/main/resources/DayplanDatabase.csv");
        verify(editInfoPresenter).prepareSuccessView(any());
    }

}
