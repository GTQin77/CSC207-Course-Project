package api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YelpFusion extends ApiHandler implements YelpInterface {
    private static final String URL = "https://api.yelp.com/v3";
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    private String getBaseUrl() {
        return URL;
    }

    @Override
    protected String getApiToken() {
        return API_TOKEN;
    }

    /**
     * Get the ids of businesses within a specified category and near the user's location.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     *
     * @param category Category chosen by ChatGPT.
     * @param city The city's name of where the user is at.
     * @param i        Number of business ID that you want.
     * @return the ids of the businesses.
     * @throws RuntimeException If API call failed or failed to extract businessID.
     */
    @Override
    public ArrayList<String> getBusinessIDs(String category, String city, Integer i) {
        String url = String.format("%s/businesses/search?categories=%s&location=%s", getBaseUrl(), category, city);
        String response = executeApiRequest(url);

        try {
            JSONObject responseBody = new JSONObject(response);
            JSONArray businessList = responseBody.getJSONArray("businesses");
            ArrayList<String> businessIds = new ArrayList<>();
            for (int ind = 0; ind < i && ind < businessList.length(); ind++) {
                JSONObject business = businessList.getJSONObject(ind);
                businessIds.add(business.getString("id"));
            }
            return businessIds;
        } catch (JSONException e) {
            throw new RuntimeException("Failed to extract business IDs", e);
        }
    }


    /**
     * Get the information of a business when given the businessID.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     *
     * @param businessID ID of the business.
     * @return the name, rating, price, contact number, distance to user and location of the business.
     * @throws RuntimeException If API call failed or failed to extract business details.
     */
    @Override
    public ArrayList<Object> getBusiness(String businessID) {
        String url = String.format("%s/businesses/%s", getBaseUrl(), businessID);
        String response = executeApiRequest(url);
        return parseBusinessDetails(response);
    }

    private ArrayList<Object> parseBusinessDetails(String response) {
        try {
            JSONObject responseBody = new JSONObject(response);
            ArrayList<Object> details = new ArrayList<>();

            details.add(responseBody.getString("name"));
            details.add(responseBody.getFloat("rating"));
            details.add(responseBody.has("price") ? responseBody.getString("price") : "Information unavailable");
            details.add(responseBody.getString("phone"));
            details.add(parseCoordinates(responseBody.getJSONObject("coordinates")));

            return details;
        } catch (JSONException e) {
            throw new RuntimeException("Failed to parse business details", e);
        }
    }

    private ArrayList<Double> parseCoordinates(JSONObject coordinates) throws JSONException {
        ArrayList<Double> locationBusiness = new ArrayList<>();
        locationBusiness.add(coordinates.getDouble("latitude"));
        locationBusiness.add(coordinates.getDouble("longitude"));
        return locationBusiness;
    }


    /**
     * Get three reviews of a business corresponding to a specific businessID.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     * @param businessID ID of the business.
     * @return three reviews of the business.
     * @throws RuntimeException If API call failed or failed to extract reviews.
     */
    @Override
    public JSONArray getBusinessReviews(String businessID) {
        String url = String.format("%s/businesses/%s/reviews", getBaseUrl(), businessID);
        String response = executeApiRequest(url);

        try {
            JSONObject responseBody = new JSONObject(response);
            return responseBody.getJSONArray("reviews");
        } catch (JSONException e) {
            throw new RuntimeException("Failed to extract reviews", e);
        }
    }
}

