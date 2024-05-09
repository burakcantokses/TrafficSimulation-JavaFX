package burak.tokses.game;

import burak.tokses.ui.path.Path;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Car> cars = new ArrayList<>();
    private Group root;
    private List<Path> paths;

    private double time = 0;


    public Game(List<Path> paths, Group root) {
        this.paths = paths;
        this.root = root;
    }

    public void createTraffic() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    private void update() {
        time += 0.16; // Adjust this value as needed

        // Move each car
        List<Car> carsToRemove = new ArrayList<>();
        for (Car car : cars) {
            // Check if the car is still on the path
            if (car.isOnPath()) {
                car.move();
            }
            // Check if the car has reached its destination
            if (car.hasReachedDestination()) {
                carsToRemove.add(car);
            }
        }

        // Remove cars that have reached their destination
        cars.removeAll(carsToRemove);
        for (Car car : carsToRemove) {
            root.getChildren().remove(car.getShape());
        }

        // Spawn a new car at less frequent intervals
        if (time > 2) { // Adjust this value as needed
            if (Math.random() < 0.1) { // Adjust this value as needed
                spawnCar();
            }
            time = 0;
        }
    }


    public void spawnCar() {
        // Choose a random path
        int randomIndex = (int) (Math.random() * paths.size());
        Path path = paths.get(randomIndex);

        // Get the start point of the path
        double x = path.getStartPoint().getX();
        double y = path.getStartPoint().getY();

        // Define car properties
        double width = 20;
        double height = 10;
        double speed = 0.5;
        double acceleration = 0.2; // Adjust as needed
        double maxSpeed = 1; // Adjust as needed

        // Randomly set the direction to either "X" or "Y"
        String direction = Math.random() < 0.5 ? "X" : "Y";

        // Create a new car at the start point
        Car car = new Car(x, y, width, height, speed, acceleration, maxSpeed, direction, path);

        // Add the car to the list of cars
        cars.add(car);

        // Add the car's shape to the root group to be displayed
        root.getChildren().add(car.getShape());
    }
}