package api;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class OpenAI implements OpenInterface{
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_TOKEN = System.getenv("API_TOKEN_2");
    public static String getApiToken() {
        return API_TOKEN;
    }

    /**
     * Get a category from OpenAI GPT-4o-mini model.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     *
     * @param userMessage User description of the day that he/she wants.
     * @return a category name.
     */
    @Override
    public String getCategory(String userMessage) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // setting up the input to GPT
        String model = "gpt-4o-mini";
        JSONObject callBody = getObject(userMessage, model);

        // build request
        return getAPICallString(client, callBody);
    }


    /**
     * Get the three words descriptor of the user's dayplan.
     * <p>
     * This implementation closely follows the grade-api in Tutorial 3 on
     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
     * </p>
     *
     * @param businessesNames A string containing the businesses name in user's dayplan.
     * @return three words describing the vibe of today.
     */
    @Override
    public String getVibe(String businessesNames) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // again, setting up the input to GPT
        String model = "gpt-4o-mini";
        JSONObject callBody = getJsonObject(businessesNames, model);

        // build request
        return getAPICallString(client, callBody);
    }


    /**
     * Helper method for getCategory and getVibe.
     * @param client Http client for calling the API.
     * @param callBody the body of API call.
     * @return a category name or three words description of the day.
     */
    @NotNull
    private String getAPICallString(OkHttpClient client, JSONObject callBody) {
        RequestBody body = RequestBody.create(callBody.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()) {
                JSONArray choices = responseBody.getJSONArray("choices");
                JSONObject choiceOne = choices.getJSONObject(0);
                JSONObject GPTresponse = choiceOne.getJSONObject("message");
                String responseContent = GPTresponse.getString("content");
                return responseContent.replace("\n", "");

            } else {
                throw new RuntimeException("Failed to call OpenAI API.");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method of the getCategory, this builds the API call.
     * @param userMessage User description of the day that he/she wants.
     * @param model Model of GPT that is using, always gpt-4o-mini in our case.
     * @return the body of the API call for get Category.
     */
    private static @NotNull JSONObject getObject(String userMessage, String model) {
        JSONObject callBody = new JSONObject();
        callBody.put("model", model); // model that you want to use

        JSONObject message = new JSONObject();
        JSONArray messages = new JSONArray();
        message.put("role", "user");
        String note = " , please select one word from the following that you think matches this message most: ";
        String categories = "active, arts.";
        String warn = " Note that you can only return one word.";
        String prompt = "Given "+ userMessage + note + categories + warn;
        message.put("content", prompt);
        messages.put(message);

        callBody.put("messages", messages);
        callBody.put("temperature", 0.2); // set to 0.2 to gives strict response
        return callBody;
    }


    /**
     * Helper method of the getVibe.
     * @param businessesNames A string containing the businesses name in user's dayplan.
     * @param model Model of GPT that is using, always gpt-4o-mini in our case.
     * @return the body of the API call for getVibe.
     */
    private static @NotNull JSONObject getJsonObject(String businessesNames, String model) {
        JSONObject callBody = new JSONObject();
        callBody.put("model", model); // model that you want to use

        JSONObject message = new JSONObject();
        JSONArray messages = new JSONArray();
        message.put("role", "user");
        String pre = "Suppose that a person went to the following locations in his day: ";
        String note = ". Please return three words that you think suit this person's day.";
        String prompt = pre + businessesNames + note;
        message.put("content", prompt);
        messages.put(message);

        callBody.put("messages", messages);
        callBody.put("temperature", 0.2); // set to 0.2 to gives strict response
        return callBody;
    }

}
