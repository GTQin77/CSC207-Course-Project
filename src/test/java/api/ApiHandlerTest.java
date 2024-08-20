package api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ApiHandlerTest {
    private ApiHandler apiHandler;
    private OkHttpClient client;
    private Call call;

    @BeforeEach
    void setUp() {
        apiHandler = spy(new ApiHandler() {
            @Override
            protected String getApiToken() {
                return "test-token";
            }
        });
        client = mock(OkHttpClient.class);
        call = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        doReturn(client).when(apiHandler).createHttpClient();
    }

    @Test
    void testExecuteApiRequest_Get_Success() throws IOException {
        Response response = new Response.Builder()
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("OK")
                .body(ResponseBody.create("{\"key\": \"value\"}", MediaType.parse("application/json")))
                .build();
        when(call.execute()).thenReturn(response);

        String result = apiHandler.executeApiRequest("http://example.com");
        assertEquals("{\"key\": \"value\"}", result);
    }

    @Test
    void testExecuteApiRequest_Get_Failure() throws IOException {
        when(call.execute()).thenThrow(new IOException("Failed to connect"));

        Exception exception = assertThrows(RuntimeException.class, () -> apiHandler.executeApiRequest("http://example.com"));
        assertEquals("Network error occurred", exception.getMessage());
    }

    @Test
    void testExecuteApiRequest_Post_Success() throws IOException {
        Response response = new Response.Builder()
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("OK")
                .body(ResponseBody.create("{\"success\": true}", MediaType.parse("application/json")))
                .build();
        when(call.execute()).thenReturn(response);

        String result = apiHandler.executeApiRequest("http://example.com", "{\"param\": \"value\"}");
        assertEquals("{\"success\": true}", result);
    }

    @Test
    void testExecuteApiRequest_Post_Failure() throws IOException {
        when(call.execute()).thenThrow(new IOException("Failed to connect"));

        Exception exception = assertThrows(RuntimeException.class, () -> apiHandler.executeApiRequest("http://example.com", "{\"param\": \"value\"}"));
        assertEquals("Network error occurred", exception.getMessage());
    }

    @Test
    void testExecuteApiRequest_UnsuccessfulResponseWithBody() throws IOException {
        Response response = new Response.Builder()
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(404).message("Not Found")
                .body(ResponseBody.create("Error details", MediaType.parse("text/plain")))
                .build();
        when(call.execute()).thenReturn(response);

        Exception exception = assertThrows(RuntimeException.class, () -> apiHandler.executeApiRequest("http://example.com"));
        assertTrue(exception.getMessage().contains("API call failed: Error details"));
    }

    @Test
    void testExecuteApiRequest_UnsuccessfulResponseWithoutBody() throws IOException {
        Response response = new Response.Builder()
                .request(new Request.Builder().url("http://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(404).message("Not Found")
                .body(null)
                .build();
        when(call.execute()).thenReturn(response);

        Exception exception = assertThrows(RuntimeException.class, () -> apiHandler.executeApiRequest("http://example.com"));
        assertTrue(exception.getMessage().contains("API call failed: No response body"));
    }
}
