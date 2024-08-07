package use_case.edit_info;

import entity.User;

public class EditInfoOutputData {

    private User user;
    private String message;
    private boolean useCaseFailed;

    public EditInfoOutputData(User user, String message, boolean useCaseFailed) {
        this.user = user;
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {return user;}

    public String getMessage() {return message;}

    public boolean getUseCaseFailed() {return useCaseFailed;}

}
