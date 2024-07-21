package entity;

import java.util.ArrayList;

public interface BusinessFactory {
    Business create(String name, ArrayList<Float> location, float distance, String contactNum,
                    String price, String rating);
}
