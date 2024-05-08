package burak.tokses.ui.traffic;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

// TrafficLight
//TrafficLight is a shape that consists of a line and a circle. In the input file the start and end posi
//tions to draw the line will be given. The circle needs to be at the center point of the line. Traffic
//Light should also be able to change color when it’s clicked. Red and green are the two color op
//tions.
public class TrafficLight {
    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public String color;

    public TrafficLight(double x1, double y1, double x2, double y2, String color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

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
}