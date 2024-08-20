package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Abstract class defining a framework for handling API requests.
 */
public abstract class ApiHandler {

    /**
     * Retrieves the API token for authorization headers.
     * @return the API token as a {@link String}.
     */
    protected abstract String getApiToken();

    /**
     * Executes an API GET request using a specified URL.
     * @param url The URL of the API endpoint.
     * @return A string containing the response body if successful.
     */
    protected String executeApiRequest(String url) {
        OkHttpClient client = createHttpClient();
        Request request = buildGetRequest(url);
        return executeRequest(client, request);
    }

    /**
     * Executes an API POST request using a specified URL and payload.
     * @param url  The URL of the API endpoint.
     * @param json The JSON string to be sent as the request's payload.
     * @return A string containing the response body if successful.
     */
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

    /**
     * Executes an HTTP request and returns the response body as a string.
     * @param client  The OkHttpClient instance used to execute the request.
     * @param request The Request object containing all the details of the HTTP request.
     * @return The response body as a string if the request is successful.
     * @throws RuntimeException if the request fails or if there is no response body.
     */
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

    /**
     * Creates a new instance of OkHttpClient.
     * @return An instance of OkHttpClient.
     */
    protected OkHttpClient createHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    /**
     * Builds a GET request with the necessary authorization and accept headers.
     * @param url The URL to which the request will be made.
     * @return A configured Request object ready for execution.
     */
    protected Request buildGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getApiToken())
                .addHeader("accept", "application/json")
                .build();
    }
}
