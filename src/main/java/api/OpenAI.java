package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class that extends ApiHandler to handle API requests specific to the OpenAI API.
 */
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

    /**
     * Parses the JSON response from the OpenAI API to extract the desired text content.
     *
     * @param response The JSON string response from the API.
     * @return Extracted text content from the JSON response.
     * @throws RuntimeException if there are issues in parsing the response or if the response is unsuccessful.
     */
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

    /**
     * Constructs a JSON object for an API request based on a user message and a model.
     * This JSON object includes details like the role, content, and the model specifications.
     *
     * @param userMessage The user's message or prompt for the API.
     * @param model The model of GPT to use for generating responses.
     * @param isMeal Boolean flag to determine if the prompt relates to meals.
     * @return A JSONObject representing the body of the API request.
     */
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

    /**
     * Generates a structured prompt for the OpenAI API based on user input and context.
     *
     * @param businessesNames String containing business names for the day's plan.
     * @param model The model of GPT specified for use.
     * @return A JSONObject containing the structured prompt for API requests.
     */
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

    /**
     * Helper method to generate a formatted prompt for the OpenAI API based on the user's input.
     *
     * @param userMessage Description provided by the user.
     * @param isMeal Boolean indicating if the category is related to meals.
     * @return A formatted prompt for the API.
     */
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
