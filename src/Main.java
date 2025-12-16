import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/login", new Login());
        server.createContext("/evaluate", new Evaluation());

        server.start();

        System.out.println("Java backend running at http://localhost:8080");
    }
}
