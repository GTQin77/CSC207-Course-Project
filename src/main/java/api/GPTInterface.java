package api;

import org.json.JSONException;

public interface GPTInterface {

    String pickCategory(String prompt) throws JSONException;

    String getVibe(String location) throws  JSONException;
}
