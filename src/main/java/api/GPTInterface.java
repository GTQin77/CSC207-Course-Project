package api;

import org.json.JSONException;

public interface GPTInterface {

    String pickCategory(String prompt);

    String getVibe(String location);

    String getPrompt() throws JSONException;

    String setPrompt(String prompt) throws JSONException;
}
