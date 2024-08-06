package entity;

import api.YelpFusion;

import java.util.ArrayList;

public interface BusinessFactory {
    Business createBusiness(String businessID, ArrayList<Double> userLocation);
}
