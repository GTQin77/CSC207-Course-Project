package use_case.business_detail;

// As a user, I want to see the general details of a business
// (eg. location, name, image, and review highlights), so that I can
// decide whether the suggested business is somewhere that I would like to go.

import entity.Business;

public class BusinessInputData {
    final private Business event;

    public BusinessInputData(Business event) {
        this.event = event;
    }

    public Business getEvent() {
        return event;
    }
}
