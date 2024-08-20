package api;
import java.util.ArrayList;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class YelpFusionTest {

    private YelpFusion yelpFusion;

    @BeforeEach
    void setUp() {
        yelpFusion = spy(new YelpFusion());
        doReturn("https://api.yelp.com/v3").when(yelpFusion).getBaseUrl();
        doReturn("fake_api_token").when(yelpFusion).getApiToken();
    }

    @Test
    void getBaseUrl() {
        String url = yelpFusion.getBaseUrl();
        assertNotNull(url);
    }

    @Test
    void getApiToken() {
        String token = System.getenv("API_TOKEN");
        assertNotNull(token);
    }

    @Test
    void getBusinessID() throws Exception {
        String category = "food";
        String cityName = "Toronto";
        int numIDs = 2;

        String jsonResponse = "{\"businesses\": [{\"id\": \"abc123\"}, {\"id\": \"def456\"}]}";
        doReturn(jsonResponse).when(yelpFusion).executeApiRequest(anyString());

        ArrayList<String> businessIDs = yelpFusion.getBusinessIDs(category, cityName, numIDs);

        assertNotNull(businessIDs);
        assertEquals(2, businessIDs.size());
    }

    @Test
    void testGetBusinessIDsError() {
        doThrow(new RuntimeException("API call failed")).when(yelpFusion).executeApiRequest(anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> yelpFusion.getBusinessIDs("restaurants", "San Francisco", 2));
        assertTrue(exception.getMessage().contains("API call failed"));
    }

    @Test
    void getBusiness() throws Exception {
        String businessID = "north-india-restaurant-san-francisco";

        String jsonResponse = "{\"name\": \"Yelp Eatery\", \"rating\": 4.5, \"price\": \"$\", \"phone\": \"1234567890\", \"coordinates\": {\"latitude\": 37.7749, \"longitude\": -122.4194}}";
        doReturn(jsonResponse).when(yelpFusion).executeApiRequest(anyString());

        ArrayList<Object> businessDetails = yelpFusion.getBusiness(businessID);

        assertNotNull(businessDetails);
        assertEquals(5, businessDetails.size());
        assertInstanceOf(String.class, businessDetails.get(0));
        assertInstanceOf(Float.class, businessDetails.get(1));
        assertInstanceOf(String.class, businessDetails.get(2));
        assertInstanceOf(String.class, businessDetails.get(3));
        assertInstanceOf(ArrayList.class, businessDetails.get(4));
    }

    @Test
    void testGetBusinessError() {
        doReturn("invalid json").when(yelpFusion).executeApiRequest(anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> yelpFusion.getBusiness("abc123"));
        assertTrue(exception.getMessage().contains("Failed to parse business details"));
    }

    @Test
    void testBusinessIDsFewerThanRequested() throws Exception {
        String jsonResponse = "{\"businesses\": [{\"id\": \"abc123\"}]}";
        doReturn(jsonResponse).when(yelpFusion).executeApiRequest(anyString());

        ArrayList<String> result = yelpFusion.getBusinessIDs("restaurants", "Smalltown", 5);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBusinessDetailsMissingFields() throws Exception {
        String jsonResponse = "{\"name\": \"Missing Info\", \"rating\": 4.2}";
        doReturn(jsonResponse).when(yelpFusion).executeApiRequest(anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> yelpFusion.getBusiness("missing-fields"));
        assertTrue(exception.getMessage().contains("Failed to parse business details"));
    }

    @Test
    void testGetBusinessCoordinatesError() throws Exception {
        String jsonResponse = "{\"name\": \"Test\", \"rating\": 4.2, \"coordinates\": {\"latitude\": \"not_a_number\"}}";
        doReturn(jsonResponse).when(yelpFusion).executeApiRequest(anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> yelpFusion.getBusiness("coord-error"));
        assertTrue(exception.getMessage().contains("Failed to parse business details"));
    }

    @Test
    void testGetBusinessIDsJsonError() throws Exception {
        doReturn("invalid json").when(yelpFusion).executeApiRequest(anyString());

        Exception exception = assertThrows(RuntimeException.class, () -> yelpFusion.getBusinessIDs("restaurants", "Nowhere", 1));
        assertTrue(exception.getMessage().contains("Failed to extract business IDs"));
    }

}
