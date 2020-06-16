import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import metro.Connection;
import metro.Line;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String MAP_FILE = "./src/main/resources/map.json";

    public static void main(String[] args) {
        System.out.println("Программа парсинга станций Московского метрополитена\n");
        try {
            createJson(createMetroMap(), MAP_FILE);

            JSONObject jsonMetroMap = (JSONObject) new JSONParser().parse(new FileReader(MAP_FILE));
            Map<String, Integer> countStations = countStations(jsonMetroMap, readLines(jsonMetroMap));

            printStationsCount(countStations);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> createMetroMap() throws IOException {
        Document doc = Jsoup.parse(Jsoup.connect(URL).get().toString());
        List<Line> linesWithNumbers = parseLines(doc);
        Map<String, ArrayList<String>> stationsMap = parseStations(doc);
        Map<Integer, List<Connection>> connectionsMap = parseConnections(doc);

        Map<String, Object> metroMap = new HashMap<>();
        metroMap.put("stations", stationsMap);
        metroMap.put("connections", connectionsMap.values().toArray());
        metroMap.put("lines", linesWithNumbers);
        return metroMap;
    }

    private static List<Line> parseLines(Document doc) {
        Elements lines = doc.getElementsByClass("js-metro-line");
        List<Line> linesWithNumbers = new ArrayList<>();
        lines.forEach(line -> linesWithNumbers.add(new Line(line.text(), line.dataset().get("line"))));
        return linesWithNumbers;
    }

    private static Map<String, ArrayList<String>> parseStations(Document doc) {
        Elements lineStations = doc.getElementsByClass("js-metro-stations");
        Map<String, ArrayList<String>> stationsMap = new TreeMap<>();
        lineStations.forEach(line -> line.getElementsByClass("name")
                .forEach(station -> {
                    if (stationsMap.containsKey(line.dataset().get("line"))) {
                        stationsMap.get(line.dataset().get("line")).add(station.text());
                    } else {
                        stationsMap.put(line.dataset().get("line"), new ArrayList<>((Collections.singletonList(station.text()))));
                    }
                }));
        return stationsMap;
    }

    private static Map<Integer, List<Connection>> parseConnections(Document doc) {
        Elements connectionsElements = doc.getElementsByClass("t-icon-metroln");
        List<Element> connections = connectionsElements.stream().filter(connection -> !connection.hasClass("t-metrostation-list-header"))
                .collect(Collectors.toList());
        Map<Integer, List<Connection>> connectionsMap = new HashMap<>();
        AtomicInteger key = new AtomicInteger();
        connections.forEach(connection -> {
            String connectionTitle = connection.attr("title");
            String stationName = connectionTitle.substring(connectionTitle.indexOf('«') + 1, connectionTitle.indexOf('»'));
            String HTMLclass = connection.className();
            Connection currentConnection = new Connection(stationName, HTMLclass.substring(HTMLclass.lastIndexOf("ln-") + 3));
            //  every new connections array starts with station, which has "name" html class
            if (connection.previousElementSibling().hasClass("name")) {
                key.getAndIncrement();
                connectionsMap.put(key.intValue(), new ArrayList<>(Arrays.asList(
                        new Connection(connection.previousElementSibling().text(),
                                connection.closest(".t-metrostation-list-table").attr("data-line")),
                        currentConnection
                )));
            } else {
                connectionsMap.get(key.intValue()).add(currentConnection);
            }
        });
        System.out.println("Количество переходов в метро: " + connectionsMap.values().toArray().length);
        return connectionsMap;
    }

    private static void createJson(Map<String, Object> map, String mapFile) throws IOException {
        try (FileWriter writer = new FileWriter(mapFile)) {
            Gson gson = new GsonBuilder().create();
            JsonElement json = gson.toJsonTree(map);
            writer.write(String.valueOf(json));
        }
    }

    private static Map<String, String> readLines(JSONObject jsonMetroMap) {
        List<Map<String, String>> lines = (List<Map<String, String>>) jsonMetroMap.get("lines");
        Map<String, String> linesMap = new HashMap<>();
        lines.forEach(line -> linesMap.put(line.get("number"), line.get("name")));
        return linesMap;
    }

    private static Map<String, Integer> countStations(JSONObject jsonMetroMap, Map<String, String> linesMap) {
        Map<String, Integer> stationsCount = new HashMap<>();
        Map<String, List<String>> stationsToCount = (Map<String, List<String>>) jsonMetroMap.get("stations");
        stationsToCount.keySet().forEach(stations -> stationsCount.put(linesMap.get(stations), stationsToCount.get(stations).size()));
        return stationsCount;
    }

    private static void printStationsCount(Map<String, Integer> countStations) {
        System.out.println("Количество станций на линиях метро:");
        System.out.println("------------------------------------");
        countStations.keySet().forEach(stations -> System.out.printf("%-32s  %2d\n", stations, countStations.get(stations)));
    }
}