package burak.tokses.ui.build;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

//Building
//There are three types of buildings with four different colors. Type 0 and type 1 buildings are big
//ger buildings representing public buildings in the cities whereas type 3 is a smaller building rep
//resenting houses. There are also four different colors to make the buildings more diverse. Build
//ing class takes type, rotation and color as parameters to draw the buildings. As you will see in
//the level parsing section, input file includes color index for buildings. It means you need to have
//an array of four colors, and you will give the color at given index to the building. You can define
//your own colors for the buildings. Other than the shape differences, different type buildings has
//no difference. They act as starting points and destination points for the cars. You can see the
//Building types and their rotated versions in the image below.
public class Building {
    public int type;
    public int rotation;
    public int color;
    public int x;
    public int y;

    public Building(int type, int rotation, int color, int x, int y) {
        this.type = type;
        this.rotation = rotation;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Node toNode() {
        System.out.println(x + " " + y + " " + type + " " + rotation + " " + color);
        // Assuming each grid cell is 50x50 pixels
        int cellSize = 50;

        switch (type) {
            case 0:
                return createType0Building(x, y, cellSize, rotation, color);
            case 1:
                return createType1Building(x, y, cellSize, color);
            case 2:
                return createBuildingRectangle(x, y, cellSize, rotation, color);
            default:
                return null;
        }
    }
    private Group createType0Building(int x, int y, int cellSize, int rotation, int color) {
        // Outer rectangle
        Rectangle outerRectangle = new Rectangle(x * cellSize - 10, y * cellSize - 5, cellSize * 2.5, cellSize * 3.5);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EEF8FE"));
        outerRectangle.setStroke(Color.BLACK);

        // Create a rectangle for the main building
        Rectangle mainRectangle = new Rectangle(x * cellSize, y * cellSize, cellSize * 2, cellSize * 2);
        mainRectangle.setArcWidth(12); // Set the arc width for rounded corners
        mainRectangle.setArcHeight(12); // Set the arc height for rounded corners
        mainRectangle.setFill(getColor(color)); // Set the fill color

        // Calculate the size for the inner contour (10% smaller than the main rectangle)
        double innerSize = cellSize * 2 * 0.85;

        // Calculate the position for the inner contour (centered inside the main rectangle)
        double innerX = x * cellSize + (cellSize * 2 - innerSize) / 2;
        double innerY = y * cellSize + (cellSize * 2 - innerSize) / 2;

        // Create the inner contour rectangle
        Rectangle innerRectangle = new Rectangle(innerX, innerY, innerSize, innerSize);
        innerRectangle.setArcWidth(10); // Set the arc width for rounded corners
        innerRectangle.setArcHeight(10); // Set the arc height for rounded corners
        innerRectangle.setFill(getColor(color)); // Set the fill color (a bit darker)
        innerRectangle.setStroke(getDarkerColor(color)); // Set the fill color (a bit darker)
        innerRectangle.setStrokeWidth(5); // Set the stroke width

        // Rotate both rectangles based on the rotation in degrees
        mainRectangle.setRotate(rotation);
        innerRectangle.setRotate(rotation);

        // Create a group to hold both rectangles
        Group group = new Group(outerRectangle, mainRectangle, innerRectangle);

        return group;
    }
    // Utility method to get color based on index
    private Color getColor(int colorIndex) {
        switch (colorIndex) {
            case 0:
                return Color.web("#FECB9B");
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }
    private Color getDarkerColor(int colorIndex) {
        Color color = getColor(colorIndex);
        return color.darker();
    }
    public Circle createType1Building(int x, int y, int cellSize, int color) {
        // Create a circle for the building
        Circle circle = new Circle((x + 0.5) * cellSize, (y + 0.5) * cellSize, cellSize / 2);

        // Set the fill color based on the color index
        switch (color) {
            case 0:
                circle.setFill(Color.web("#FECB9B"));
                break;
            case 1:
                circle.setFill(Color.GREEN);
                break;
            case 2:
                circle.setFill(Color.BLUE);
                break;
            case 3:
                circle.setFill(Color.YELLOW);
                break;
        }

        return circle;
    }

    public Rectangle createBuildingRectangle(int x, int y, int cellSize, int rotation, int color) {
        // Create a rectangle for the building with rounded corners
        Rectangle rectangle = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        rectangle.setArcWidth(12); // Set the arc width for rounded corners
        rectangle.setArcHeight(12); // Set the arc height for rounded corners

        // Set the fill color based on the color index
        switch (color) {
            case 0:
                rectangle.setFill(Color.web("#FECB9B"));
                break;
            case 1:
                rectangle.setFill(Color.GREEN);
                break;
            case 2:
                rectangle.setFill(Color.BLUE);
                break;
            case 3:
                rectangle.setFill(Color.YELLOW);
                break;
        }

        // Convert the rotation from degrees to radians
        double rotationInRadians = Math.toRadians(rotation);

        // Rotate the rectangle based on the rotation in radians
        rectangle.setRotate(rotationInRadians);

        return rectangle;
    }
}