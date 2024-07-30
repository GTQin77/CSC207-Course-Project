package use_case.refresh;

import entity.Dayplan;

public class RefreshInputData {
    final private Dayplan dayplan;
    final private Boolean refreshAll;
    final private Integer refreshIndex;

    public RefreshInputData(Dayplan dayplan, Boolean refreshAll, Integer refreshIndex) {
        this.dayplan  = dayplan;
        this.refreshAll = refreshAll;
        this.refreshIndex = refreshIndex;
    }

    Dayplan getDayplan() {
        return dayplan;
    }

    Boolean getRefreshAll() {
        return refreshAll;
    }

    Integer getRefreshIndex() {
        return refreshIndex;
    }
}
