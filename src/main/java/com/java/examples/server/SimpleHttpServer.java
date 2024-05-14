package com.java.examples.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        // Create a new HTTP server on port 8000
        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(8000), 0);

        // Create a context for the root path "/"
        server.createContext("/", new MyHandler());

        // Start the server
        server.start();

        System.out.println("Server is listening on port 8000");
    }

    // Handler class to handle incoming requests
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "text/plain");

            // Send response code and write response body
            String response = "Hello from the Java HTTP Server!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
