package use_case.refresh;

import entity.Dayplan;

/**
 * Output data for refreshing day plan.
 */
public class RefreshOutputData {

    private final Dayplan dayplan;

    public RefreshOutputData(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public Dayplan getDayplan() {return dayplan;}

}
