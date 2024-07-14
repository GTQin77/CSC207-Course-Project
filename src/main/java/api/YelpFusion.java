package api;

import entity.Events;
import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class YelpFusion implements YelpInterface{
    private static final String URL = "";
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    public static String getApiToken() {
        return API_TOKEN;
    }

    @Override
    public String getBusinessID(String keyword, ArrayList<Float> location) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.yelp.com/v3/businesses/search?term=%s&latitude=%s&longitude=%s",
                        keyword, location.get(0), location.get(1)))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        return null;
    }

    @Override
    public Events getEvents(String businessID) {
        return null;
    }
}
