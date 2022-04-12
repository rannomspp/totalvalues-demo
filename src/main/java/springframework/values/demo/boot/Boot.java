package springframework.values.demo.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.values.demo.domain.Value;
import springframework.values.demo.repositories.JSONRepository;
import springframework.values.demo.repositories.ValueRepository;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class Boot implements CommandLineRunner {
    private final JSONRepository jsonRepository;
    private final ValueRepository valueRepository;

    public Boot(JSONRepository jsonRepository, ValueRepository valueRepository) {
        this.jsonRepository = jsonRepository;
        this.valueRepository = valueRepository;
    }

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public void run(String... args) throws Exception {
        HttpRequest requestJSON_POST = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/set_values"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(readFileAsString("./src/main/resources/json/values.json")))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(requestJSON_POST, HttpResponse.BodyHandlers.ofString());

        HttpRequest requestJSON_GET = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/get_values_total"))
                .header("Accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> responseJSON_GET = HttpClient.newHttpClient().send(requestJSON_GET, HttpResponse.BodyHandlers.ofString());
    }
}