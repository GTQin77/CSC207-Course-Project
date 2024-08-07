package use_case.edit_info;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class EditInfoInputData {

    private final User user;
    private final ArrayList<String> stringLocation = new ArrayList<>(2);

    public EditInfoInputData(User user) {
        this.user = user;
    }

    public String getUserName() {return this.user.getUserName();}

    public String getPassword() {return this.user.getPassword();}

    public ArrayList<String> getLocation() {

        ArrayList<Double> doubleLocation = this.user.getLocation();
        for (Double location : doubleLocation) {
            this.stringLocation.add(location.toString());
        }
        return stringLocation;
    }
}
