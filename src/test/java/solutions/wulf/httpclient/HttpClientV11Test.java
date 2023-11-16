package solutions.wulf.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class HttpClientV11Test {

    private static String URI_EVENT_DISCOVERY = "https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=INVALID";

    @Test
    public void testGetErrorInvalidApiKey() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_EVENT_DISCOVERY))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertFaultMessage(response.body());
    }

    @Test
    public void testAsyncGetInvalidApiKey() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_EVENT_DISCOVERY))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient.newBuilder()
                .build()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(HttpClientV11Test::assertFaultMessage);
    }

    private static void assertFaultMessage(String response)  {
        assertTrue(response.contains("fault"));

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            String msg = root.at("/fault/faultstring").asText();
            assertTrue(msg != null && !msg.isEmpty());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }
}