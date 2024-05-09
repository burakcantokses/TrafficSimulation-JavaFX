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

/**
 * Parses a level configuration file to extract information about buildings, roads, traffic lights, and paths.
 */
public class LevelParser {

    // Lists to store parsed objects
    private List<Building> buildings = new ArrayList<>();
    private Metadata metadata;
    private List<RoadTile> roadTiles = new ArrayList<>();
    private List<TrafficLight> trafficLights = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();

    /**
     * Parses the specified level configuration file.
     * @param metadataFile The path to the metadata file.
     */
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

    /**
     * Parses a single line from the level configuration file.
     * @param line The line to parse.
     */
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

    /**
     * Parses metadata information from the given array of string parts.
     * @param parts The string parts representing metadata.
     */
    public void parseMetadata(String[] parts) {
        double width = Double.parseDouble(parts[1]);
        double height = Double.parseDouble(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        int paths = Integer.parseInt(parts[5]);
        int carsToWin = Integer.parseInt(parts[6]);
        int allowedCrashes = Integer.parseInt(parts[7]);
        // Process metadata...
        metadata = new Metadata(width, height, gridX, gridY, paths, carsToWin, allowedCrashes);
    }

    /**
     * Parses building information from the given array of string parts.
     * @param parts The string parts representing a building.
     */
    public void parseBuilding(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int colorIndex = Integer.parseInt(parts[3]);
        int gridX = Integer.parseInt(parts[4]);
        int gridY = Integer.parseInt(parts[5]);
        // Process building...
        Building building = new Building(type, rotation, colorIndex, gridX, gridY);
        buildings.add(building);
    }

    /**
     * Parses road tile information from the given array of string parts.
     * @param parts The string parts representing a road tile.
     */
    public void parseRoadTile(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        // Process road tile...
        RoadTile roadTile = new RoadTile(type, rotation, gridX, gridY);
        roadTiles.add(roadTile);
    }

    /**
     * Parses traffic light information from the given array of string parts.
     * @param parts The string parts representing a traffic light.
     */
    public void parseTrafficLight(String[] parts) {
        double x1 = Double.parseDouble(parts[1]);
        double y1 = Double.parseDouble(parts[2]);
        double x2 = Double.parseDouble(parts[3]);
        double y2 = Double.parseDouble(parts[4]);
        String color = "green"; // Initially set the traffic light color to green
        // Process traffic light...
        TrafficLight trafficLight = new TrafficLight(x1, y1, x2, y2, color);
        trafficLights.add(trafficLight);
    }

    /**
     * Parses path information from the given array of string parts.
     * @param parts The string parts representing a path.
     */
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

    /**
     * Returns the list of buildings parsed from the level configuration.
     * @return The list of buildings.
     */
    public List<Building> getBuildings() {
        return buildings;
    }

    /**
     * Returns the metadata parsed from the level configuration.
     * @return The metadata.
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Returns the list of road tiles parsed from the level configuration.
     * @return The list of road tiles.
     */
    public List<RoadTile> getRoadTiles() {
        return roadTiles;
    }

    /**
     * Returns the list of traffic lights parsed from the level configuration.
     * @return The list of traffic lights.
     */
    public List<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    /**
     * Returns the list of paths parsed from the level configuration.
     * @return The list of paths.
     */
    public List<Path> getPaths() {
        return paths;
    }
}
