import burak.tokses.game.car.Car;
import burak.tokses.game.car.CarManager;
import burak.tokses.parser.MetadataParser;
import burak.tokses.ui.build.Building;
import burak.tokses.ui.path.Path;
import burak.tokses.ui.road.RoadTile;
import burak.tokses.ui.traffic.TrafficLight;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrafficSimulatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        int level = 0;
        String levelFile = "level" + level + ".txt";
        MetadataParser levelParser = new MetadataParser();
        levelParser.parseFile(levelFile);

        Group root = new Group();

        // Izgara çizimi
        int gridX = levelParser.metadata.getGridX();
        int gridY = levelParser.metadata.getGridY();
        double cellWidth = levelParser.metadata.getWidth() / gridX;
        double cellHeight = levelParser.metadata.getHeight() / gridY;

        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                Rectangle cell = new Rectangle(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT); // Doldurma rengini şeffaf yap
                cell.setStroke(Color.web("#95BED5")); // Sadece strokeli olarak ayarla
                root.getChildren().add(cell);
            }
        }

        // Kaç kaza yapılabilir
        System.out.println("Allowed Crashes: " + levelParser.metadata.getAllowedCrashes());
        // Ekrana ekle text olarak ekle
        root.getChildren().add(new Text(10, 20, "Allowed Crashes: " + levelParser.metadata.getAllowedCrashes()));

        // Kazanmak için kaç araba gerekiyor
        System.out.println("Cars to Win: " + levelParser.metadata.getCarsToWin());
        // Ekrana ekle text olarak ekle
        root.getChildren().add(new Text(10, 40, "Cars to Win: " + levelParser.metadata.getCarsToWin()));



        // Yolları çiz
        for (RoadTile roadTile : levelParser.roadTiles) {
            roadTile.draw(root, cellWidth, cellHeight);
        }

        // Binaları çiz
        for (Building building : levelParser.buildings) {
            root.getChildren().add(building.toNode(cellWidth, cellHeight));
        }

        // Trafik ışıklarını çiz
        for (TrafficLight trafficLight : levelParser.trafficLights) {
            trafficLight.draw(root, cellWidth);
        }

        // Create a CarManager
        CarManager carManager = new CarManager(levelParser.trafficLights);

        // Create cars and add them to the CarManager
        for (Path path : levelParser.paths) {
            // Check if the first command of the path is a MoveTo command
            if (path.getCommands().get(0).equals("MoveTo")) {
                double x = path.getXPoints().get(0);
                double y = path.getYPoints().get(0);
                double speed = 0.5; // Set the speed of the car
                double width = cellWidth * 0.2; // Set the width of the car
                double height = cellHeight * 0.4; // Set the height of the car
                Car car = new Car(x, y, speed, width, height, path);
                System.out.println("Created car at (" + x + ", " + y + ") with size (" + width + ", " + height + ")");
                carManager.addCar(car, root);
            }
        }
        // Start the CarManager
        //carManager.start();

        Scene scene = new Scene(root, levelParser.metadata.getWidth(), levelParser.metadata.getHeight());
        scene.setFill(Color.web("#9BC6DF"));
        primaryStage.setTitle("Traffic Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
