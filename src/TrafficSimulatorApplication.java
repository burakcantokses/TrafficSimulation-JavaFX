import burak.tokses.game.Game;
import burak.tokses.parser.LevelParser;
import burak.tokses.ui.build.Building;
import burak.tokses.ui.road.RoadTile;
import burak.tokses.ui.traffic.TrafficLight;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A JavaFX application for simulating traffic behavior on a grid-based road network.
 */
public class TrafficSimulatorApplication extends Application {

    /** File format for level configuration files. */
    private static final String LEVEL_FILE_FORMAT = "level%d.txt";
    public static String LEVEL_FILE_PATH = "";

    /** Text format for displaying allowed crashes in the game. */
    private static final String ALLOWED_CRASHES_TEXT = "Allowed Crashes: %d";

    /** Text format for displaying the number of cars needed to win the game. */
    private static final String CARS_TO_WIN_TEXT = "Cars to Win: %d";

    /**
     * Starts the traffic simulator application.
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Level to be loaded
        String levelFile = String.format(MainMenu.level == - 1 ? LEVEL_FILE_PATH :LEVEL_FILE_FORMAT, MainMenu.level);
        LevelParser levelParser = new LevelParser();
        levelParser.parseFile(levelFile);

        // Root group for holding all graphical elements
        Group root = new Group();

        // Draw grid lines
        drawGrid(root, levelParser);

        // Display game information
        displayGameInfo(root, levelParser);

        // Draw roads
        drawRoads(root, levelParser);

        // Draw buildings
        drawBuildings(root, levelParser);

        // Draw traffic lights
        drawTrafficLights(root, levelParser);

        // Start the simulation
        startSimulation(root, levelParser);

        // Create scene and set background color
        Scene scene = new Scene(root, levelParser.getMetadata().getWidth(), levelParser.getMetadata().getHeight());
        scene.setFill(Color.web("#9BC6DF"));

        // Set stage title and scene
        primaryStage.setTitle("Traffic Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Draws grid lines on the scene based on the level metadata.
     * @param root The root group for adding grid lines.
     * @param levelParser The parser containing level metadata.
     */
    private void drawGrid(Group root, LevelParser levelParser) {
        int gridX = levelParser.getMetadata().getGridX();
        int gridY = levelParser.getMetadata().getGridY();
        double cellWidth = levelParser.getMetadata().getWidth() / gridX;
        double cellHeight = levelParser.getMetadata().getHeight() / gridY;

        for (int i = 0; i < gridX; i++) {
            for (int j = 0; j < gridY; j++) {
                Rectangle cell = new Rectangle(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.setStroke(Color.web("#95BED5"));
                root.getChildren().add(cell);
            }
        }
    }

    /**
     * Displays game information such as allowed crashes and cars needed to win.
     * @param root The root group for adding text elements.
     * @param levelParser The parser containing level metadata.
     */
    private void displayGameInfo(Group root, LevelParser levelParser) {
        root.getChildren().add(new Text(10, 20, String.format(ALLOWED_CRASHES_TEXT, levelParser.getMetadata().getAllowedCrashes())));
        root.getChildren().add(new Text(10, 40, String.format(CARS_TO_WIN_TEXT, levelParser.getMetadata().getCarsToWin())));
    }

    /**
     * Draws road tiles on the scene based on the level configuration.
     * @param root The root group for adding road tiles.
     * @param levelParser The parser containing level metadata and road tiles.
     */
    private void drawRoads(Group root, LevelParser levelParser) {
        for (RoadTile roadTile : levelParser.getRoadTiles()) {
            roadTile.draw(root, levelParser.getMetadata().getWidth() / levelParser.getMetadata().getGridX(), levelParser.getMetadata().getHeight() / levelParser.getMetadata().getGridY());
        }
    }

    /**
     * Draws buildings on the scene based on the level configuration.
     * @param root The root group for adding buildings.
     * @param levelParser The parser containing level metadata and buildings.
     */
    private void drawBuildings(Group root, LevelParser levelParser) {
        for (Building building : levelParser.getBuildings()) {
            root.getChildren().add(building.toNode(levelParser.getMetadata().getWidth() / levelParser.getMetadata().getGridX(), levelParser.getMetadata().getHeight() / levelParser.getMetadata().getGridY()));
        }
    }

    /**
     * Draws traffic lights on the scene based on the level configuration.
     * @param root The root group for adding traffic lights.
     * @param levelParser The parser containing level metadata and traffic lights.
     */
    private void drawTrafficLights(Group root, LevelParser levelParser) {
        for (TrafficLight trafficLight : levelParser.getTrafficLights()) {
            trafficLight.draw(root, levelParser.getMetadata().getWidth() / levelParser.getMetadata().getGridX());
        }
    }

    /**
     * Starts the simulation with the specified root group and level parser.
     * @param root
     * @param levelParser
     */
    private void startSimulation(Group root, LevelParser levelParser) {
        Game game = new Game(levelParser.getPaths(), levelParser.getTrafficLights(), root);
        game.createTraffic();
    }
    /**
     * Main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
