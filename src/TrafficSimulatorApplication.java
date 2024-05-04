import burak.tokses.parser.MetadataParser;
import burak.tokses.ui.build.Building;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TrafficSimulatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        MetadataParser levelParser = new MetadataParser();
        levelParser.parseFile("metadata.txt");

        Group root = new Group();
        System.out.println(levelParser.buildings.size());
        for (Building building : levelParser.buildings) {
            root.getChildren().add(building.toNode());
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
