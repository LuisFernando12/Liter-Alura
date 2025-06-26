package br.com.luis.fernando.LiterAlura.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Fetch implements IFetch{
    private HttpRequest request;
    private  HttpClient client = HttpClient.newHttpClient();
    @Override
    public HttpResponse<String> get(String uri) {
        if (uri.contains(" ")){
            uri = uri.replace(" ", "%20");
        }
       this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.request = HttpRequest.newBuilder(URI.create(uri)).GET().build();
        try {
            return this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
