import burak.tokses.parser.MetadataParser;
import burak.tokses.ui.build.Building;
import burak.tokses.ui.road.RoadTile;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficSimulatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        MetadataParser levelParser = new MetadataParser();
        levelParser.parseFile("metadata.txt");

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

        // Yolları çiz
        for (RoadTile roadTile : levelParser.roadTiles) {
            roadTile.draw(root, cellWidth, cellHeight);
        }

        // Binaları çiz
        for (Building building : levelParser.buildings) {
            root.getChildren().add(building.toNode(cellWidth, cellHeight));
        }

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
