package use_case.edit_info;

import entity.User;

public interface EditInfoInputBoundary {
    User execute(EditInfoInputData editInfoInputData);
}
