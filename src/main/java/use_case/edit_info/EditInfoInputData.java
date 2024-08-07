package use_case.edit_info;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class EditInfoInputData {

    private final String userName;
    private final String password;
    private final String repeatPassword;
    private final String location;
    private final User user;
    private final ArrayList<String> stringLocation = new ArrayList<>(2);

    public EditInfoInputData(String userName, String password, String repeatPassword,
                             String location, User user) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.location = location;
        this.user = user;
    }

    public String getUserName() {return this.userName;}

    public String getPassword() {return this.password;}

    public String getRepeatPassword() {return this.repeatPassword;}

    public String getLocation() {
        return this.location;
    }

    public User getUser() {
        return this.user;
    }
}
