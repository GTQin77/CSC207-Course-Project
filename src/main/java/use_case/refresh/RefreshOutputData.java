package use_case.refresh;

import entity.Dayplan;

public class RefreshOutputData {

    private final Dayplan dayplan;
    // private boolean refreshFailed;

    public RefreshOutputData(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public Dayplan getDayplan() {return dayplan;}

}
