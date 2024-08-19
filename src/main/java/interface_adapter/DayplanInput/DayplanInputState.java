package interface_adapter.DayplanInput;

public class DayplanInputState {
    private String city = "";
    private String cityError = null;
    private int numMeals = 0;
    private int numActivities = 0;
    private String description = "";

    public DayplanInputState(DayplanInputState copy) {
        city = copy.city;
        cityError = copy.cityError;
        numMeals = copy.numMeals;
        numActivities = copy.numActivities;
        description = copy.description;
    }

    public DayplanInputState() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityError() {
        return cityError;
    }

    public void setCityError(String cityError) {
        this.cityError = cityError;
    }

    public int getNumMeals() {
        return numMeals;
    }

    public void setNumMeals(Integer numMeals) {
        this.numMeals = numMeals;
    }

    public int getNumActivities() {
        return numActivities;
    }

    public void setNumActivities(Integer numActivities) {
        this.numActivities = numActivities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
