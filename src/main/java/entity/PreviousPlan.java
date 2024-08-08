package entity;

import java.util.ArrayList;

public class PreviousPlan {
    private ArrayList<PreviousBusiness> businesses;

    public PreviousPlan() {
        this.businesses = new ArrayList<>();
    }

    public void addBusiness(PreviousBusiness previousBusiness) {
        this.businesses.add(previousBusiness);
    }

    public PreviousBusiness getBusinesses(int index) {
        return this.businesses.get(index);
    }

    public int getSize() {
        return this.businesses.size();
    }
}
