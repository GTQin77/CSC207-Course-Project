package entity;

import api.OpenInterface;
import api.YelpInterface;


import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


    public OpenInterface getOpenApi() {
        return this.openApi;
    }

    public void setYelpApi(YelpInterface yelpApi) {
        this.yelpApi = yelpApi;
    }

    public OpenInterface getOpenApi() {
        return this.openApi;
    }

    public void setYelpApi(YelpInterface yelpApi) {
        this.yelpApi = yelpApi;
    }

    public Map.Entry<Business, String> generateNewBusiness(Dayplan dayplan, String type) {

    public Business generateNewBusiness(Dayplan dayplan, String type) {

                Business newBusiness = this.businessFactory.createBusiness(newID, user.getLocation());
                return new AbstractMap.SimpleEntry<>(newBusiness, newID);
            }
        }
        throw new RuntimeException("No new unique businesses found.");
    }

                return this.businessFactory.createBusiness(newID, user.getLocation());
            }
        }
        throw new RuntimeException("No new unique businesses found.");

    }

}
}

//    public Business generateNewBusiness(Dayplan dayplan, String type) {
//        User user = dayplan.getUser();
//        String city = dayplan.getCity();
//        String description = dayplan.getDescription();
//        ArrayList<String> prevBusinessIDs = dayplan.getBusinessIDs();
//
//        boolean isMeal = this.isMeal(type);
//
//        String category = this.openApi.getCategory(description, isMeal);
//
//        ArrayList<String> newBusinessIDs = this.yelpApi.getBusinessIDs(category, city, 20);
//
//        int i = 0;
//        while (prevBusinessIDs.contains(newBusinessIDs.get(i))){       // NOTE: add a case to handle if all id's are same?
//            i = i + 1;
//        }
//
//        return this.businessFactory.createBusiness(newBusinessIDs.get(i), user.getLocation());
//    }
