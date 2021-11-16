import clients.StopsClient;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        String path = currentPath + "/src/main/resources/stops.txt";
        var stopsClient = new StopsClient(path);
        var stops = stopsClient.parse();
        stops.forEach(stop -> System.out.println(stop.getName() + " " + stop.getLocation()));
    }
}
