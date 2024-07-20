package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenAITest {

    @Test
    void getApiToken() {
        String token = OpenAI.getApiToken();
        assertNotNull(token);
    }

    @Test
    void getCategory() {
        String userMessage = "I want an exciting day.";

        OpenAI openAI = new OpenAI();

        String category = openAI.getCategory(userMessage);
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