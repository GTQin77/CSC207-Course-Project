package entity;

import api.YelpFusion;

public interface BusinessFactory {
    Business createBusiness(String businessID);
}
