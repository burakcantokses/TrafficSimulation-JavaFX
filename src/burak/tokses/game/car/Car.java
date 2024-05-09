package burak.tokses.game.car;

import burak.tokses.ui.path.Path;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Car {
    public double x;
    public double y;
    public double speed;
    public double width;
    public double height;
    public Path path;
    public boolean isCrashed;
    public String status;

    public Car(double x, double y, double speed, double width, double height, Path path) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.path = path;
        this.isCrashed = false;
        this.status = "Moving";
    }

    public void move() {
        if (status.equals("Moving")) {
            x += speed;
        }
    }

    public void stop() {
        status = "Stopped";
    }

    public void crash() {
        isCrashed = true;
        status = "Crashed";
    }

    public void repair() {
        isCrashed = false;
        status = "Moving";
    }

    public void draw(Group root) {
        javafx.scene.shape.Rectangle carShape = new Rectangle(x, y, width, height);
        carShape.setFill(Color.BLUE);
        root.getChildren().add(carShape);
    }

}