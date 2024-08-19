package use_case.refresh;

import entity.Dayplan;

/**
 * Input data for refreshing day plan.
 */
public class RefreshInputData {
    final private Dayplan dayplan;

    public RefreshInputData(Dayplan dayplan) {
        this.dayplan  = dayplan;
    }

    Dayplan getDayplan() {
        return dayplan;
    }
}
