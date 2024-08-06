package use_case.edit_info;

import java.util.ArrayList;
import java.util.List;

public class EditInfoInputData {
    private String userName;
    private String password;
    private String repeatPassword;
    private ArrayList<String> location;

    public EditInfoInputData(String userName, String password, String repeatPass,
                             String location) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPass;
        this.location = new ArrayList<String>((List.of(location.split(","))));
    }

    public String getUserName() {return this.userName;}

    public String getPassword() {return this.password;}

    public String getRepeatPassword() {return this.repeatPassword;}

    public ArrayList<String> getLocation() {return this.location;}
}
