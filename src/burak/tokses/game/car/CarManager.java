package burak.tokses.game.car;

import burak.tokses.ui.traffic.TrafficLight;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class CarManager {
    private List<Car> cars;
    private List<TrafficLight> trafficLights;

    public CarManager(List<TrafficLight> trafficLights) {
        this.cars = new ArrayList<>();
        this.trafficLights = trafficLights;
    }

    public void addCar(Car car, Group root) {
        cars.add(car);
        car.draw(root);
        // Add the car to the root group
    }

    public void start() {
        for (Car car : cars) {
            // Check if the car is at a red light
            for (TrafficLight trafficLight : trafficLights) {
                if (trafficLight.isRed() && carIsAtTrafficLight(car, trafficLight)) {
                    car.stop();
                } else {
                    car.resume();
                }
            }
            car.move();
        }
    }

    private boolean carIsAtTrafficLight(Car car, TrafficLight trafficLight) {
        // Implement the logic to check if the car is at the traffic light
        return false;
    }
}