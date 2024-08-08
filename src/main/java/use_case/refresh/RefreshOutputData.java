package use_case.refresh;

import entity.Dayplan;

public class RefreshOutputData {

    private final Dayplan dayplan;

    public RefreshOutputData(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public Dayplan getDayplan() {return dayplan;}

}
