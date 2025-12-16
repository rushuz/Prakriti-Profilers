import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Evaluation implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Handle CORS preflight
        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        // Read request body
        String body = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        // Count answers
        int vata = count(body, "VATA");
        int pitta = count(body, "PITTA");
        int kapha = count(body, "KAPHA");

        int total = vata + pitta + kapha;
        if (total == 0) total = 1;

        int vataPct = (vata * 100) / total;
        int pittaPct = (pitta * 100) / total;
        int kaphaPct = (kapha * 100) / total;

        String result;
        if (vata >= pitta && vata >= kapha) result = "VATA";
        else if (pitta >= kapha) result = "PITTA";
        else result = "KAPHA";

        String response = String.format("""
        {
          "vata": %d,
          "pitta": %d,
          "kapha": %d,
          "result": "%s"
        }
        """, vataPct, pittaPct, kaphaPct, result);

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }

    private int count(String body, String key) {
        return body.split(key, -1).length - 1;
    }
}
