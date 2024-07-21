package api;

import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class YelpFusion implements YelpInterface{
    private static final String URL = "https://api.yelp.com/v3";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    public static String getApiToken() {
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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/search?categories=%s&location=%s",
                        category, city))
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                JSONArray businessList = responseBody.getJSONArray("businesses");

                ArrayList<String> businessIds = new ArrayList<>();
                for (int ind = 0; ind < i; ind++) {
                    JSONObject business = businessList.getJSONObject(ind);
                    businessIds.add(business.getString("id"));
                }
                return businessIds;

            } else {
                throw new RuntimeException(responseBody.getString("code"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/%s",
                        businessID))
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                String name = responseBody.getString("name");
                Float rating = responseBody.getFloat("rating");
                String price = responseBody.getString("price");
                String contactNum = responseBody.getString("phone");

                JSONObject coordinates = responseBody.getJSONObject("coordinates");
                double latitude = coordinates.getDouble("latitude");
                double longitude = coordinates.getDouble("longitude");

                ArrayList<Float> locationBusiness = new ArrayList<>();
                locationBusiness.add((float) latitude);
                locationBusiness.add((float) longitude);

                ArrayList<Object> details = new ArrayList<>();
                details.add(name);
                details.add(rating);
                details.add(price);
                details.add(contactNum);
                details.add(locationBusiness);

                return details;

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/%s/reviews",
                        businessID))
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                return responseBody.getJSONArray("reviews");

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
