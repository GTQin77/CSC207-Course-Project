package api;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OpenAI extends ApiHandler implements OpenInterface {
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_TOKEN = System.getenv("API_TOKEN_2");

    @Override
    protected String getBaseUrl() {
        return URL;
    }

    @Override
    protected String getApiToken() {
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
     * @param isMeal A boolean indicates whether the business is a meal or activity.
     * @return a category name.
     */
    @Override
    public String getCategory(String userMessage, boolean isMeal) {
        JSONObject callBody = getObject(userMessage, "gpt-4o-mini", isMeal);
        String response = executeApiRequest(getBaseUrl(), callBody.toString());
        return parseResponse(response);
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
        JSONObject callBody = getJsonObject(businessesNames, "gpt-4o-mini");
        String response = executeApiRequest(getBaseUrl(), callBody.toString());
        return parseResponse(response);
    }


    private String parseResponse(String response) {
        try {
            JSONObject responseBody = new JSONObject(response);
            JSONArray choices = responseBody.getJSONArray("choices");
            JSONObject choiceOne = choices.getJSONObject(0);
            JSONObject GPTresponse = choiceOne.getJSONObject("message");
            String responseContent = GPTresponse.getString("content");
            return responseContent.replace("\n", "");
        } catch (JSONException e) {
            throw new RuntimeException("Failed to parse response from OpenAI API.", e);
        }
    }

    private static JSONObject getObject(String userMessage, String model, boolean isMeal) {
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", generatePrompt(userMessage, isMeal));
        messages.put(message);

        JSONObject callBody = new JSONObject();
        callBody.put("model", model);
        callBody.put("messages", messages);
        callBody.put("temperature", 0.2); // Set to low to minimize unexpected responses
        return callBody;
    }

    private static JSONObject getJsonObject(String businessesNames, String model) {
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", "Suppose that a person went to the following locations in his day: " +
                businessesNames + ". Please return three words that you think suit this person's day.");
        messages.put(message);

        JSONObject callBody = new JSONObject();
        callBody.put("model", model);
        callBody.put("messages", messages);
        callBody.put("temperature", 0.2);
        return callBody;
    }

    private static String generatePrompt(String userMessage, boolean isMeal) {
        String categoryOptions = isMeal ?
                "\"african, tradamerican, arabian, bbq, bistros, breakfast_brunch, buffets, burgers, cafes, " +
                        "carribean, chickenshop, chinese, cuban, diners, dumplings, hotdogs, filipino, fishnchips, " +
                        "french, greek, halal, indian, italian, japanese, korean, mexican, mediterranean, noodles, " +
                        "oriental, panasian, pubfood, salad, seafood, soulfood, soup, steakhouses, sushi bars, vegan, " +
                        "vegetarian, vietnamese, thai, sandwiches, portuguese, mideastern\"" :
                "\"active, arts, beautysvc, nightlife, shopping\"";

        return "Given the prompt: \"" + userMessage + "\", select one category from the following that matches the prompt most: " +
                categoryOptions + ". Only return the category name.";
    }
}
//
//public class OpenAI extends ApiHandler implements OpenInterface{
//    private static final String URL = "https://api.openai.com/v1/chat/completions";
//    private static final String API_TOKEN = System.getenv("API_TOKEN_2");
//
//    @Override
//    protected String getBaseUrl() {
//        return URL;
//    }
//
//    protected String getApiToken() {
//        return API_TOKEN;
//    }
//
//    /**
//     * Get a category from OpenAI GPT-4o-mini model.
//     * <p>
//     * This implementation closely follows the grade-api in Tutorial 3 on
//     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
//     * </p>
//     *
//     * @param userMessage User description of the day that he/she wants.
//     * @return a category name.
//     */
//    @Override
//    public String getCategory(String userMessage, boolean isMeal) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//
//        // setting up the input to GPT
//        String model = "gpt-4o-mini";
//        JSONObject callBody = getObject(userMessage, model, isMeal);
//
//        // build request
//        return getAPICallString(client, callBody);
//    }
//
//
//    /**
//     * Get the three words descriptor of the user's dayplan.
//     * <p>
//     * This implementation closely follows the grade-api in Tutorial 3 on
//     * <a href="https://github.com/Yasamanro/grade-api">github.com</a>.
//     * </p>
//     *
//     * @param businessesNames A string containing the businesses name in user's dayplan.
//     * @return three words describing the vibe of today.
//     */
//    @Override
//    public String getVibe(String businessesNames) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//
//        // again, setting up the input to GPT
//        String model = "gpt-4o-mini";
//        JSONObject callBody = getJsonObject(businessesNames, model);
//
//        // build request
//        return getAPICallString(client, callBody);
//    }
//
//
//    /**
//     * Helper method for getCategory and getVibe.
//     * @param client Http client for calling the API.
//     * @param callBody the body of API call.
//     * @return a category name or three words description of the day.
//     */
//    @NotNull
//    private String getAPICallString(OkHttpClient client, JSONObject callBody) {
//        RequestBody body = RequestBody.create(callBody.toString(), MediaType.parse("application/json"));
//        Request request = new Request.Builder()
//                .url("https://api.openai.com/v1/chat/completions")
//                .addHeader("Authorization", "Bearer " + API_TOKEN)
//                .addHeader("Content-Type", "application/json")
//                .post(body)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            assert response.body() != null;
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (response.isSuccessful()) {
//                JSONArray choices = responseBody.getJSONArray("choices");
//                JSONObject choiceOne = choices.getJSONObject(0);
//                JSONObject GPTresponse = choiceOne.getJSONObject("message");
//                String responseContent = GPTresponse.getString("content");
//                return responseContent.replace("\n", "");
//
//            } else {
//                throw new RuntimeException("Failed to call OpenAI API.");
//            }
//        } catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Helper method of the getCategory, this builds the API call.
//     *
//     * @param userMessage User description of the day that he/she wants.
//     * @param model       Model of GPT that is using, always gpt-4o-mini in our case.
//     * @return the body of the API call for get Category.
//     */
//    private static @NotNull JSONObject getObject(String userMessage, String model, boolean isMeal) {
//        JSONObject callBody = new JSONObject();
//        callBody.put("model", model); // model that you want to use
//
//        JSONObject message = new JSONObject();
//        JSONArray messages = new JSONArray();
//        message.put("role", "user");
//        String note;
//        String categories;
//        String warn = ". Only return the category name.";
//        if (isMeal){
//            note = ", select one category of food from the following that matches the prompt most: ";
//            categories = "\"african, tradamerican, arabian, bbq, bistros, breakfast_brunch, buffets, burgers, cafes, " +
//                    "carribean, chickenshop, chinese, cuban, diners, dumplings, hotdogs, filipino, fishnchips, " +
//                    "french, greek, halal, indian, italian, japanese, korean, mexican, mediterranean, noodles, " +
//                    "oriental, panasian, pubfood, salad, seafood, soulfood, soup, steakhouses, sushi bars, vegan, " +
//                    "vegetarian, vietnamese, thai, sandwiches, portuguese, mideastern\"";
//        }
//        else{
//            note = ", select one category from the following that matches the prompt most: ";
//            categories = "\"active, arts, beautysvc, nightlife, shopping\"";
//        }
//        String prompt = "Given the prompt:" + "\"" + userMessage + "\"" + note + categories + warn;
//        message.put("content", prompt);
//        messages.put(message);
//
//        callBody.put("messages", messages);
//        callBody.put("temperature", 0.2); // set to 0.2 to gives strict response
//        return callBody;
//    }
//
//
//    /**
//     * Helper method of the getVibe.
//     * @param businessesNames A string containing the businesses name in user's dayplan.
//     * @param model Model of GPT that is using, always gpt-4o-mini in our case.
//     * @return the body of the API call for getVibe.
//     */
//    private static @NotNull JSONObject getJsonObject(String businessesNames, String model) {
//        JSONObject callBody = new JSONObject();
//        callBody.put("model", model); // model that you want to use
//
//        JSONObject message = new JSONObject();
//        JSONArray messages = new JSONArray();
//        message.put("role", "user");
//        String pre = "Suppose that a person went to the following locations in his day: ";
//        String note = ". Please return three words that you think suit this person's day.";
//        String prompt = pre + businessesNames + note;
//        message.put("content", prompt);
//        messages.put(message);
//
//        callBody.put("messages", messages);
//        callBody.put("temperature", 0.2); // set to 0.2 to gives strict response
//        return callBody;
//    }
//
//}
