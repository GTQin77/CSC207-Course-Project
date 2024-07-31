package use_case.distance;

import use_case.dayplanList.UserDayPlanInputBoundary;

import java.util.ArrayList;

public class DistanceInteractor implements DistanceInputBoundary {
    // DAO for distance??? prob not
    final DistanceOutputBoundary distanceOutputBoundary;
    // prob no factories for this either

    /**
     * Interactor for the distance difference use case.
     * @param distanceOutputBoundary Output boundary for use case.
     * */
    public DistanceInteractor(DistanceOutputBoundary distanceOutputBoundary) {
        this.distanceOutputBoundary = distanceOutputBoundary;
    }

    @Override
    public double execute(DistanceInputData inputData) {
        ArrayList<Double> userLocation = inputData.getLocation();
        ArrayList<Double> businessLocation = inputData.getBusinessLocation();
        double difference;
        difference = getDifference(userLocation, businessLocation);

        return difference;
    }


    /**
     * Calculate the difference between two ArrayLists with longitude and latitude.
     * @param location1 is at ArrayList[0]. Negative numbers are west.
     * @param location2 is at ArrayList[1]. Negative numbers are south.
     * @return a double that is the distance between the two locations
     * */
    private static double getDifference(ArrayList<Double> location1, ArrayList<Double> location2) {
        double differenceNm;
        double radLong1;
        double radLong2;
        double radLat1;
        double radLat2;
        //IN NM
        double radiusEarth = 3440.1;
        double haversineInput;
        double differenceKm;

        radLong1 = Math.toRadians(location1.get(0));
        radLong2 = Math.toRadians(location2.get(0));
        radLat1 = Math.toRadians(location1.get(1));
        radLat2 = Math.toRadians(location2.get(1));

        haversineInput = (Math.sin(radLat1) * Math.sin(radLat2)) + Math.cos(radLat1) *
                Math.cos(radLat2) * Math.cos(radLong1 - radLong2);

        // Haversine Formula
        differenceNm = radiusEarth * Math.acos(haversineInput);
        differenceKm = differenceNm * 1.852;

        return differenceKm;
    }
}
