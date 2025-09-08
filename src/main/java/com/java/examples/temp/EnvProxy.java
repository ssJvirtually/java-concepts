package com.java.examples.temp;

import java.io.*;
import java.net.*;
import java.util.*;

public class EnvProxy {
    private static final Map<String, String> ENV_PROXY_MAP = Map.of(
            "dev-api.myapp.com", "dev-proxy.company.com:8080",
            "sit-api.myapp.com", "sit-proxy.company.com:8080",
            "ppd-api.myapp.com", "ppd-proxy.company.com:8080"
    );

    public static void main(String[] args) throws IOException {
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Local Env Proxy running on port " + port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        }
    }

    private static void handleClient(Socket client) {
        try (client) {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            OutputStream out = client.getOutputStream();

            String requestLine = in.readLine();
            if (requestLine == null) return;

            if (requestLine.startsWith("CONNECT")) {
                // HTTPS tunnel
                String[] parts = requestLine.split(" ");
                String hostPort = parts[1];
                String host = hostPort.split(":")[0];
                int port = Integer.parseInt(hostPort.split(":")[1]);

                // Pick which proxy to forward to
                String target = ENV_PROXY_MAP.get(host);
                if (target == null) {
                    out.write("HTTP/1.1 502 Bad Gateway\r\n\r\n".getBytes());
                    return;
                }

                String[] targetParts = target.split(":");
                try (Socket targetSocket = new Socket(targetParts[0], Integer.parseInt(targetParts[1]))) {
                    out.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                    out.flush();

                    // Start piping raw TLS data
                    Thread t1 = new Thread(() -> pipe(client, targetSocket));
                    Thread t2 = new Thread(() -> pipe(targetSocket, client));
                    t1.start();
                    t2.start();
                    t1.join();
                    t2.join();
                }
            } else {
                // Handle plain HTTP requests
                out.write("HTTP/1.1 501 Not Implemented\r\n\r\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pipe(Socket src, Socket dest) {
        try (InputStream in = src.getInputStream(); OutputStream out = dest.getOutputStream()) {
            in.transferTo(out);
        } catch (IOException ignored) {}
    }
}
