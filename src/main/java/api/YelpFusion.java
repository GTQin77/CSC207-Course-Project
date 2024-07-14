package api;

import entity.Events;
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

    // change the search term to category
    @Override
    public String getBusinessID(String category, ArrayList<Float> location, int i) {
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
                JSONObject business = businesses.getJSONObject(i);

                return business.getString("business_id");

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Events getEvents(String businessID) {
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

                return new Events(name, rating, price, location);

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JSONArray getEventsReviews(String businessID) {
        return null;
    }

}

