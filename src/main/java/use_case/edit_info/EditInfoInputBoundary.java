package use_case.edit_info;

import entity.User;
import services.UserService;

public interface EditInfoInputBoundary {
    User execute(EditInfoInputData editInfoInputData, UserService userService);

}
