package interface_adapter.DayplanInput;

public class DayplanInputState {
    private String city = "";
    private String cityError = null;
    private Integer numMeals = 0;
    private String numMealsError = null;
    private Integer numActivities = 0;
    private String numActivitiesError = null;
    private String description = "";
    private String descriptionError = null;

    public DayplanInputState(DayplanInputState copy) {
        city = copy.city;
        cityError = copy.cityError;
        numMeals = copy.numMeals;
        numMealsError = copy.numMealsError;
        numActivities = copy.numActivities;
        numActivitiesError = copy.numActivitiesError;
        description = copy.description;
        descriptionError = copy.descriptionError;
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

    public Integer getNumMeals() {
        return numMeals;
    }

    public void setNumMeals(Integer numMeals) {
        this.numMeals = numMeals;
    }

    public String getNumMealsError() {
        return numMealsError;
    }

    public void setNumMealsError(String numMealsError) {
        this.numMealsError = numMealsError;
    }

    public Integer getNumActivities() {
        return numActivities;
    }

    public void setNumActivities(Integer numActivities) {
        this.numActivities = numActivities;
    }

    public String getNumActivitiesError() {
        return numActivitiesError;
    }

    public void setNumActivitiesError(String numActivitiesError) {
        this.numActivitiesError = numActivitiesError;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }
}
