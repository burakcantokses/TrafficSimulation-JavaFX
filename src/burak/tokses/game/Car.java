package burak.tokses.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Car {
    private Rectangle shape;
    private double speed;

    public Car(double x, double y, double width, double height, double speed) {
        this.shape = new Rectangle(x, y, width, height);
        this.shape.setFill(Color.PURPLE);
        this.speed = speed;
    }

    public Rectangle getShape() {
        return shape;
    }

    public double getSpeed() {
        return speed;
    }

    public void move() {
        shape.setX(shape.getX() + speed);
    }

    public void stop() {
        speed = 0;
    }

    public void resume(double speed) {
        this.speed = speed;
    }
}