package api;

import org.json.JSONException;

public interface GPTInterface {

    String getPrompt() throws JSONException;

    void setPrompt(String prompt);

    void pickCategory();

    String getCategory() throws JSONException;

    void pickVibe(String location);

    String getVibe();
}
