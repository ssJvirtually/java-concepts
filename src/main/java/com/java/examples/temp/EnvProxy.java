import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class EnvProxy {
    private static final Map<String, String> ENV_PROXY_MAP = Map.of(
        "dev-api.myapp.com", "http://dev-proxy.company.com:8080",
        "sit-api.myapp.com", "http://sit-proxy.company.com:8080",
        "ppd-api.myapp.com", "http://ppd-proxy.company.com:8080"
    );

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        server.createContext("/", new ProxyHandler());
        server.setExecutor(null);
        System.out.println("Local Proxy running on http://localhost:8888");
        server.start();
    }

    static class ProxyHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String host = exchange.getRequestHeaders().getFirst("Host");
            String targetProxy = ENV_PROXY_MAP.getOrDefault(host, null);

            if (targetProxy == null) {
                String msg = "No proxy mapping for host: " + host;
                exchange.sendResponseHeaders(502, msg.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(msg.getBytes());
                }
                return;
            }

            // Forward request
            URL url = new URL(targetProxy + exchange.getRequestURI());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(exchange.getRequestMethod());

            // Copy headers
            exchange.getRequestHeaders().forEach((k, v) -> conn.setRequestProperty(k, String.join(",", v)));

            // Copy body if present
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod()) || "PUT".equalsIgnoreCase(exchange.getRequestMethod())) {
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    exchange.getRequestBody().transferTo(os);
                }
            }

            // Send back response
            exchange.sendResponseHeaders(conn.getResponseCode(), conn.getInputStream().available());
            try (InputStream is = conn.getInputStream(); OutputStream os = exchange.getResponseBody()) {
                is.transferTo(os);
            }
        }
    }
}
