package app;

import interface_adapter.EditInfo.EditInfoViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.UserService;
import view.EditInfoView;

import static org.junit.jupiter.api.Assertions.*;

class EditInfoUseCaseFactoryTest {
    private ViewManagerModel viewManagerModel;
    private EditInfoViewModel editInfoViewModel;
    private UserService userService;

    @BeforeEach
    void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        editInfoViewModel = Mockito.mock(EditInfoViewModel.class);
        userService = Mockito.mock(UserService.class);
    }

    @Test
    void create() {
        EditInfoView editInfoView = EditInfoUseCaseFactory.create(viewManagerModel, editInfoViewModel, userService);
        assertNotNull(editInfoView);
    }
}