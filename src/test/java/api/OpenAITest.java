package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OpenAITest {

    @Mock
    private OpenAI openAI;

    @BeforeEach
    void setUp() {
        openAI = spy(new OpenAI());
        doReturn("fake_api_token").when(openAI).getApiToken();
        doReturn("https://api.openai.com/v1/chat/completions").when(openAI).getBaseUrl();
    }

    @Test
    void testGetCategory() {
        String userMessage = "I want an exciting day.";
        String mockResponse = "{\"choices\":[{\"message\":{\"content\":\"Adventure\"}}]}";

        doReturn(mockResponse).when(openAI).executeApiRequest(anyString(), anyString());

        String category = openAI.getCategory(userMessage, false);
        assertNotNull(category);
        assertEquals("Adventure", category);
    }

    @Test
    void testGetVibe() {
        String businessesNames = "Pizza Hut, IKEA, Apple Store";
        String mockResponse = "{\"choices\":[{\"message\":{\"content\":\"Casual, Fun, Busy\"}}]}";

        doReturn(mockResponse).when(openAI).executeApiRequest(anyString(), anyString());

        String vibe = openAI.getVibe(businessesNames);
        assertNotNull(vibe);
        assertEquals("Casual, Fun, Busy", vibe);
    }

    @Test
    void testApiFailure() {
        doThrow(new RuntimeException("API call failed")).when(openAI).executeApiRequest(anyString(), anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> openAI.getCategory("Test failure", true));
        assertTrue(exception.getMessage().contains("API call failed"));
    }

    @Test
    void testParseResponseError() {
        String badJson = "not a json";
        doReturn(badJson).when(openAI).executeApiRequest(anyString(), anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> openAI.getCategory("bad input", false));
        assertTrue(exception.getMessage().contains("Failed to parse response from OpenAI API."));
    }


}
