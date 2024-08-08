package entity;

import java.util.Map;

public interface RefreshBusinessFactory {
    Map.Entry<Business, String> generateNewBusiness(Dayplan dayplan, String type);
    boolean isMeal(String type);
}
