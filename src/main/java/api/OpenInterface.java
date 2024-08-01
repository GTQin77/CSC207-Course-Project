package api;

/**
 * Interface of the openAI api, detailed description of the method can be found at OpenAI.java.
 */
public interface OpenInterface {
    String getCategory(String userMessage, boolean isMeal);
    String getVibe(String businessesNames);
}
