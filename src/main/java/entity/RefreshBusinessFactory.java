package entity;

public interface RefreshBusinessFactory {
    Business generateNewBusiness(Dayplan dayplan, String type);
    boolean isMeal(String type);
}
