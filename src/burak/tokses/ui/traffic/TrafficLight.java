package burak.tokses.ui.traffic;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Represents a traffic light, consisting of a line and a circle.
 * The position of the line is defined by its start and end points.
 * The circle is positioned at the center of the line.
 * The traffic light can change color when clicked, toggling between red and green.
 */
public class TrafficLight {
    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public String color;

    /**
     * Constructs a traffic light with the specified parameters.
     * @param x1 The x-coordinate of the start point of the line.
     * @param y1 The y-coordinate of the start point of the line.
     * @param x2 The x-coordinate of the end point of the line.
     * @param y2 The y-coordinate of the end point of the line.
     * @param color The initial color of the traffic light (either "RED" or "GREEN").
     */
    public TrafficLight(double x1, double y1, double x2, double y2, String color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * Draws the traffic light on the specified group.
     * @param group The group to which the traffic light shapes will be added.
     * @param cellWidth The width of a grid cell, used to calculate the circle's radius.
     */
    public void draw(Group group, double cellWidth) {
        // Calculate the center point of the line
        double centerX = (x1 + x2) / 2;
        double centerY = (y1 + y2) / 2;

        // Calculate the radius of the circle (assuming a fixed size)
        double radius = cellWidth * 0.1; // Adjust radius based on your needs

        // Create a circle for the traffic light
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.valueOf(color)); // Set color based on the object's color property

        // Create a line for the pole
        Line line = new Line(x1, y1, x2, y2);

        // Add the shapes to the group
        group.getChildren().addAll(line, circle);

        // Add event handler for mouse click to change color
        circle.setOnMouseClicked(event -> {
            if (color.equals("RED")) {
                circle.setFill(Color.GREEN);
                color = "GREEN";
            } else {
                circle.setFill(Color.RED);
                color = "RED";
            }
        });
    }

    /**
     * Checks if the traffic light is currently red.
     * @return True if the traffic light is red, otherwise false.
     */
    public boolean isRed() {
        return color.equals("RED");
    }
}
