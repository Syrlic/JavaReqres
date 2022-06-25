package reqres.steps;

import api.HttpClientFactory;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reqres.utils.Constants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APISteps {
    private static HttpClient httpClient = HttpClientFactory.getClient();
    private static Logger log = LoggerFactory.getLogger(APISteps.class);


    @Step("GET request to {uri} without parameters")
    public static HttpResponse<String> getListRequestWithoutParameters(String uri) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();

        try {
            log.info("GET to " + path);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("GET request to {uri} of item {item}")
    public static HttpResponse<String> getItemRequest(String uri, String item) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri + "/" + item;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();

        try {
            log.info("GET to " + path);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("GET request to {uri} page {param}")
    public static HttpResponse<String> getRequestWithParameters(String uri, String param) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri + "?page=" + param;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();

        try {
            log.info("GET to " + path);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("PUT request to {uri} to item {item} with Json body {json}")
    public static HttpResponse<String> putRequestToCertainAddress(String uri, String item, String json) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri + "/" + item;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            log.info("PUT to " + path + " body: " + json);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("POST request to {uri} with a certain Json body {json}")
    public static HttpResponse<String> postRequestWithJsonBody(String uri, String json) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            log.info("POST to " + path + " body: " + json);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("PATCH request to {uri} to a certain item {item} with Json body {json}")
    public static HttpResponse<String> patchRequestToCertainAddress(String uri, String item, String json) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri + "/" + item;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            log.info("PATCH to " + path + " body: " + json);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode() + " body " + response.body());

        return response;
    }

    @Step("DELETE request to {uri} to a certain item {item} ")
    public static HttpResponse<String> deleteRequestToCertainAddress(String uri, String item) {

        HttpResponse<String> response = null;
        String path = Constants.BASE_URI + uri + "/" + item;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .DELETE()
                .build();

        try {
            log.info("DELETE to " + path);
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        log.info("response: code " + response.statusCode());

        return response;
    }
}
