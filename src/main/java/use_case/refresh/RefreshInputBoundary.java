package use_case.refresh;

import entity.Dayplan;

public interface RefreshInputBoundary {
    Dayplan execute(RefreshInputData refreshInputData);
}
