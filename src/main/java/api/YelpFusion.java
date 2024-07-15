package api;

import entity.Business;
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
     * Get the id of a business within a specified category and near the user's location.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     * @param category Category chosen by ChatGPT.
     * @param location Location of the user.
     * @return the id of the business.
     * @throws RuntimeException If API call failed or failed to extract businessID.
     */
    @Override
    public String getBusinessID(String category, ArrayList<Float> location) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/search?categories=%s&latitude=%s&longitude=%s",
                        category, location.get(0), location.get(1)))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONArray businesses = responseBody.getJSONArray("businesses");
                JSONObject business = businesses.getJSONObject(0);

                return business.getString("business_id");

            } else {
                throw new RuntimeException(responseBody.getString("message"));
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
     * @param businessID ID of the business.
     * @return the nate, rating, price, location of the business.
     * @throws RuntimeException If API call failed or failed to extract business details.
     */
    @Override
    public Business getBusiness(String businessID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/%s",
                        businessID))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                String name = responseBody.getString("name");
                int rating = responseBody.getInt("rating");
                String price = responseBody.getString("price");

                JSONObject coordinates = responseBody.getJSONObject("coordinates");
                double latitude = coordinates.getDouble("latitude");
                double longitude = coordinates.getDouble("longitude");

                ArrayList<Float> location = new ArrayList<>();
                location.add((float) latitude);
                location.add((float) longitude);

                return new Business(name, rating, price, location);

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
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                return responseBody.getJSONArray("reviews");

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}


