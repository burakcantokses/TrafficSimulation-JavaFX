package burak.tokses.game;

import burak.tokses.ui.path.Path;
import burak.tokses.ui.traffic.TrafficLight;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Car {
    private Rectangle shape;
    private double speed;
    private double acceleration;
    private double maxSpeed;
    private String direction;
    private Path path;
    private int currentPointIndex = 0;
    private double rotation;

    private TrafficLight trafficLight;
    private List<TrafficLight> trafficLights;


    public Car(double x, double y, double width, double height, double speed, double acceleration, double maxSpeed, String direction, Path path, List<TrafficLight> trafficLights) {
        this.shape = new Rectangle(x, y, width, height);
        this.shape.setFill(Color.PURPLE);
        this.speed = speed;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.direction = direction;
        this.path = path;
        this.rotation = 0;
        this.trafficLights = trafficLights;
        this.trafficLight = findNextTrafficLight();
    }

    public Rectangle getShape() {
        return shape;
    }

    public void accelerate() {
        if (speed + acceleration <= maxSpeed) {
            speed += acceleration;
        } else {
            speed = maxSpeed;
        }
    }

    public double getSpeed() {
        return speed;
    }

    private TrafficLight findNextTrafficLight() {
        TrafficLight next = null;
        double minDistance = Double.MAX_VALUE;
        if (currentPointIndex < path.getPoints().size() - 1) {
            Point2D currentPoint = path.getPoints().get(currentPointIndex);
            Point2D nextPoint = path.getPoints().get(currentPointIndex + 1);
            for (TrafficLight trafficLight : trafficLights) {
                double dx = trafficLight.getX1() - currentPoint.getX();
                double dy = trafficLight.getY1() - currentPoint.getY();
                double distanceToCurrentPoint = Math.sqrt(dx * dx + dy * dy);
                dx = trafficLight.getX1() - nextPoint.getX();
                dy = trafficLight.getY1() - nextPoint.getY();
                double distanceToNextPoint = Math.sqrt(dx * dx + dy * dy);
                if (distanceToCurrentPoint < minDistance && distanceToNextPoint < distanceToCurrentPoint) {
                    minDistance = distanceToCurrentPoint;
                    next = trafficLight;
                }
            }
        }
        return next;
    }
    public void move() {
        if (currentPointIndex < path.getPoints().size() - 1) {
            TrafficLight nextTrafficLight = findNextTrafficLight();
            if (nextTrafficLight != null && nextTrafficLight.isRed()) {
                // If the next traffic light is red, stop the car
                stop();
            }
            else {
                resume(maxSpeed);
                Point2D currentPoint = path.getPoints().get(currentPointIndex);
                Point2D nextPoint = path.getPoints().get(currentPointIndex + 1);

                // Calculate the direction vector from the current point to the next point
                double dx = nextPoint.getX() - currentPoint.getX();
                double dy = nextPoint.getY() - currentPoint.getY();

                // Calculate the rotation based on the direction vector
                rotation = Math.atan2(dy, dx) * 180 / Math.PI;

                // Normalize the direction vector
                double length = Math.sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;

                // Move the car along the direction vector
                shape.setX(shape.getX() + speed * dx);
                shape.setY(shape.getY() + speed * dy);

                // Set the rotation of the shape
                shape.setRotate(rotation);

                // If the car has reached the next point, increment the current point index
                if (Math.abs(shape.getX() - nextPoint.getX()) < speed && Math.abs(shape.getY() - nextPoint.getY()) < speed) {
                    currentPointIndex++;
                }
            }
        }
    }

    public boolean isOnPath() {
        return currentPointIndex < path.getPoints().size();
    }

    public boolean hasReachedDestination() {
        return currentPointIndex >= path.getPoints().size() - 1;
    }

    public void stop() {
        speed = 0;
    }

    public void resume(double speed) {
        this.speed = speed;
    }
}