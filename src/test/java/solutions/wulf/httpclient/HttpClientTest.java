package solutions.wulf.httpclient;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpClientTest {

    @Test
    public void testGetErrorInvalidApiKey() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=INVALID"))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        assertTrue(response.body().contains("fault"));
    }
}