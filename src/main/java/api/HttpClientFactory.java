package api;

import java.net.http.HttpClient;

public class HttpClientFactory {
    private static HttpClient client;

    public static HttpClient getClient() {
        if (client == null) {
            client = createClient();
        }
        return client;
    }

    private static HttpClient createClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

}
