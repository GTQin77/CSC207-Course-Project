package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OpenAI extends ApiHandler implements OpenInterface {
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_TOKEN = System.getenv("API_TOKEN_2");

    private String getBaseUrl() {
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
