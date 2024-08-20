package use_case.refresh;

import entity.Dayplan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RefreshOutputDataTest {

    @Test
    void getDayplan() {
        Dayplan expectedDayplan = new Dayplan();
        expectedDayplan.setDescription("Sample day plan");
        expectedDayplan.setCity("Sample City");

        RefreshOutputData refreshOutputData = new RefreshOutputData(expectedDayplan);

        assertEquals(expectedDayplan, refreshOutputData.getDayplan());
    }
}
