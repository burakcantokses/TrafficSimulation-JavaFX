package burak.tokses.game.car;

import burak.tokses.ui.path.Path;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Car {
    private double x;
    private double y;
    private double speed;
    private Path path;
    private double width;
    private double height;
    private String state;

    public Car(double x, double y, double speed, Path path, double cellWidth, double cellHeight) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.path = path;
        this.width = cellWidth; // Set the width of the car as 1/5 of the cell width
        this.height = cellHeight; // Set the height of the car as 1/4 of the cell height
        this.state = "MOVING";
    }

    public void move() {
        if (this.state.equals("MOVING")) {
            // Implement the logic to move the car along the path
        }
    }

    public void stop() {
        this.state = "STOPPED";
    }

    public void resume() {
        this.state = "MOVING";
    }

    public String getState() {
        return state;
    }

    public void draw(Group root) {
        Rectangle carShape = new Rectangle(x, y, width, height);
        carShape.setFill(Color.BLUE);
        root.getChildren().add(carShape);
    }
}