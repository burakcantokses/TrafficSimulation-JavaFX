package burak.tokses.parser;

import burak.tokses.ui.build.Building;
import burak.tokses.ui.metadata.Metadata;
import burak.tokses.ui.path.Path;
import burak.tokses.ui.road.RoadTile;
import burak.tokses.ui.traffic.TrafficLight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelParser {
    //list
    private List<Building> buildings = new ArrayList<Building>();
    private Metadata metadata;
    private List<RoadTile> roadTiles = new ArrayList<RoadTile>();
    private List<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
    private List<Path> paths = new ArrayList<>();

    public void parseFile(String metadataFile) {
        try {
            File file = new File(metadataFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseLine(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Metadata file not found: " + metadataFile, e);
        }
    }

    public void parseLine(String line) {
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "Metadata":
                parseMetadata(parts);
                break;
            case "Building":
                parseBuilding(parts);
                break;
            case "RoadTile":
                parseRoadTile(parts);
                break;
            case "TrafficLight":
                parseTrafficLight(parts);
                break;
            case "Path":
                parsePath(parts);
                break;
            default:
                throw new IllegalArgumentException("Unknown line type: " + line);
        }
    }

    public void parseMetadata(String[] parts) {
        double width = Double.parseDouble(parts[1]);
        double height = Double.parseDouble(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        int paths = Integer.parseInt(parts[5]);
        int carsToWin = Integer.parseInt(parts[6]);
        int allowedCrashes = Integer.parseInt(parts[7]);
        // Metadata işleme...
        metadata = new Metadata(width, height, gridX, gridY, paths, carsToWin, allowedCrashes);

    }

    public void parseBuilding(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int colorIndex = Integer.parseInt(parts[3]);
        int gridX = Integer.parseInt(parts[4]);
        int gridY = Integer.parseInt(parts[5]);
        // Building işleme...
        Building building = new Building(type, rotation, colorIndex, gridX, gridY);
        buildings.add(building);
    }

    public void parseRoadTile(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        // RoadTile işleme...
        RoadTile roadTile = new RoadTile(type, rotation, gridX, gridY);
        roadTiles.add(roadTile);
    }

    public void parseTrafficLight(String[] parts) {
        double x1 = Double.parseDouble(parts[1]);
        double y1 = Double.parseDouble(parts[2]);
        double x2 = Double.parseDouble(parts[3]);
        double y2 = Double.parseDouble(parts[4]);
        String color = "green"; // Başlangıçta trafik ışığı kırmızı olarak ayarlanır
        // TrafficLight işleme...
        TrafficLight trafficLight = new TrafficLight(x1, y1, x2, y2, color);
        trafficLights.add(trafficLight);
    }

    public void parsePath(String[] parts) {
        int pathIndex = Integer.parseInt(parts[1]);
        String command = parts[2];
        double x = Double.parseDouble(parts[3]);
        double y = Double.parseDouble(parts[4]);

        // Check if a Path with this index already exists
        Path path = paths.stream()
                .filter(p -> p.getPathIndex() == pathIndex)
                .findFirst()
                .orElse(null);

        // If the Path doesn't exist, create a new one
        if (path == null) {
            path = new Path(pathIndex);
            paths.add(path);
        }

        // Add the point to the Path
        path.addPoint(command, x, y);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public List<RoadTile> getRoadTiles() {
        return roadTiles;
    }

    public List<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public List<Path> getPaths() {
        return paths;
    }
}