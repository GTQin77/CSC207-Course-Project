package use_case.business_detail;

// As a user, I want to see the general details of a business
// (eg. location, name, image, and review highlights), so that I can
// decide whether the suggested business is somewhere that I would like to go.

import entity.Events;

public class BusinessInputData {
    final private Events event;

    public BusinessInputData(Events event) {
        this.event = event;
    }

    public Events getEvent() {
        return event;
    }
}
