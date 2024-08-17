package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenAITest {

    @Test
    void getApiToken() {
        String token = System.getenv("API_TOKEN_2");
        assertNotNull(token);
    }

    @Test
    void getCategory() {
        String userMessage = "I want an exciting day.";

        OpenAI openAI = new OpenAI();

        String category = openAI.getCategory(userMessage, false);
        assertNotNull(category);
    }

    @Test
    void getVibe() {
        String businessesNames = "Pizza Hut, IKEA, Apple Store";
        OpenAI openAI = new OpenAI();
        String vibe = openAI.getVibe(businessesNames);
        assertNotNull(vibe);
    }
}