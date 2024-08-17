package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public abstract class ApiHandler {
    protected abstract String getBaseUrl();
    protected abstract String getApiToken();

    protected String executeApiRequest(String url) {
        OkHttpClient client = createHttpClient();
        Request request = buildGetRequest(url);
        return executeRequest(client, request);
    }

    protected String executeApiRequest(String url, String json) {
        OkHttpClient client = createHttpClient();
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getApiToken())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        return executeRequest(client, request);
    }

    private String executeRequest(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new RuntimeException("API call failed: " + (response.body() != null ? response.body().string() : "No response body"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Network error occurred", e);
        }
    }

    protected OkHttpClient createHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    protected Request buildGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getApiToken())
                .addHeader("accept", "application/json")
                .build();
    }
}
