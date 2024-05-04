package burak.tokses.parser;

import burak.tokses.ui.build.Building;
import burak.tokses.ui.metadata.Metadata;
import burak.tokses.ui.path.Path;
import burak.tokses.ui.road.RoadTile;
import burak.tokses.ui.traffic.TrafficLight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelParser {
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
            System.out.println("Dosya bulunamadı.");
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
                System.out.println("Bilinmeyen satır tipi: " + line);
        }
    }

    private void parseMetadata(String[] parts) {
        double width = Double.parseDouble(parts[1]);
        double height = Double.parseDouble(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        int paths = Integer.parseInt(parts[5]);
        int carsToWin = Integer.parseInt(parts[6]);
        int allowedCrashes = Integer.parseInt(parts[7]);
        // Metadata işleme...
        Metadata metadata = new Metadata(width, height, gridX, gridY, paths, carsToWin, allowedCrashes);
    }

    private void parseBuilding(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int colorIndex = Integer.parseInt(parts[3]);
        int gridX = Integer.parseInt(parts[4]);
        int gridY = Integer.parseInt(parts[5]);
        // Building işleme...
        Building building = new Building(type, rotation, colorIndex, gridX, gridY);
    }

    private void parseRoadTile(String[] parts) {
        int type = Integer.parseInt(parts[1]);
        int rotation = Integer.parseInt(parts[2]);
        int gridX = Integer.parseInt(parts[3]);
        int gridY = Integer.parseInt(parts[4]);
        // RoadTile işleme...
        RoadTile roadTile = new RoadTile(type, rotation, gridX, gridY);
    }

    private void parseTrafficLight(String[] parts) {
        double x1 = Double.parseDouble(parts[1]);
        double y1 = Double.parseDouble(parts[2]);
        double x2 = Double.parseDouble(parts[3]);
        double y2 = Double.parseDouble(parts[4]);
        String color = "red"; // Başlangıçta trafik ışığı kırmızı olarak ayarlanır
        // TrafficLight işleme...
        TrafficLight trafficLight = new TrafficLight(x1, y1, x2, y2, color);
    }

    private void parsePath(String[] parts) {
        int pathIndex = Integer.parseInt(parts[1]);
        String command = parts[2];
        double x = Double.parseDouble(parts[3]);
        double y = Double.parseDouble(parts[4]);
        // Path işleme...
        Path path = new Path(pathIndex, command, x, y);
    }
}