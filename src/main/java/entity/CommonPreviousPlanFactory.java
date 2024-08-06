package entity;

public class CommonPreviousPlanFactory implements PreviousPlanFactory {

    @Override
    public PreviousPlan convertDayplan(String stringDayplan) {

        PreviousPlan previousPlan = new PreviousPlan();
        String[] businessMaster = stringDayplan.split("~");

        String nameBusiness = "";
        String locationBusiness = "";
        String distanceBusiness = "";
        String phoneNumber = "";
        String price = "";
        String rating = "";

        for (String business : businessMaster) {
            String[] singleBusiness = business.split(",");
            nameBusiness = singleBusiness[0];
            locationBusiness = singleBusiness[1];
            distanceBusiness = singleBusiness[2];
            phoneNumber = singleBusiness[3];
            price = singleBusiness[4];
            rating = singleBusiness[5];

            PreviousBusiness previousBusiness = new PreviousBusiness(nameBusiness, locationBusiness, distanceBusiness,
                    phoneNumber, price, rating);

            previousPlan.addBusiness(previousBusiness);
        }

        return previousPlan;
    }
}
