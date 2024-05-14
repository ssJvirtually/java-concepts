package com.java.examples.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestDemo {


    public static void main(String[] args) {

        var uri = URI.create("https://httpbin.org/get?age=26&isHappy=true");
        HttpClient httpClient = HttpClient.newHttpClient();

        //String[] headers = new String[3];

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response  = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.statusCode());
        System.out.println(response.body());




    }
}
