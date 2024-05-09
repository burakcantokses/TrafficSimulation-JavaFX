package burak.tokses.game;

import burak.tokses.ui.path.Path;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Car> cars = new ArrayList<>();
    private Group root;
    private List<Path> paths;

    public Game(List<Path> paths, Group root) {
        this.paths = paths;
        this.root = root;
    }


    public void spawnCar() {
        // Choose a random path
        int randomIndex = (int) (Math.random() * paths.size());
        Path path = paths.get(randomIndex);

        // Get the start point of the path
        double x = path.getStartPoint().getX();
        double y = path.getStartPoint().getY();

        // Create a new car at the start point
        Car car = new Car(x, y, 20, 10, 2); // Adjust size and speed as needed

        // Add the car to the list of cars
        cars.add(car);

        // Add the car's shape to the root group to be displayed
        root.getChildren().add(car.getShape());
    }
}