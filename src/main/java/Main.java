import client.SiriClient;
import client.StopsClient;

import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        String path = currentPath + "/src/main/resources/stops.txt";
        var stopsClient = new StopsClient(path);
        var stops = stopsClient.parse();
        stops.forEach(stop -> System.out.println(stop.getSiriId() + " " + stop.getName() + " " + stop.getLocation()));
        var stop = stops.stream().parallel().filter(_stop -> Objects.equals(_stop.getSiriId(), String.valueOf(1333))).findFirst();
        var siriClient = new SiriClient();
        siriClient.requestArrivals(stop.orElseThrow(RuntimeException::new)).forEach(System.out::println);
    }
}
