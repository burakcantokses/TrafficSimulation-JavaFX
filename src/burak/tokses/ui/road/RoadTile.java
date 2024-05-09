package burak.tokses.ui.road;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Represents a road tile that can be drawn on the game grid.
 * Different types of road tiles are supported, each with its own drawing logic.
 */
public class RoadTile {
    private int type;
    private int rotation;
    private double x;
    private double y;

    /**
     * Constructs a road tile with the specified parameters.
     * @param type The type of road tile.
     * @param rotation The rotation angle of the road tile.
     * @param x The x-coordinate of the road tile.
     * @param y The y-coordinate of the road tile.
     */
    public RoadTile(int type, int rotation, double x, double y) {
        this.type = type;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the road tile on the specified group.
     * @param group The group to which the road tile shapes will be added.
     * @param cellWidth The width of a grid cell.
     * @param cellHeight The height of a grid cell.
     */
    public void draw(Group group, double cellWidth, double cellHeight) {
        switch (type) {
            case 0:
                drawType0Road(group, cellWidth, cellHeight, rotation);
                break;
            case 1:
                drawType1Road(group, cellWidth, cellHeight, rotation);
                break;
            case 2:
                drawType2Road(group, cellWidth, cellHeight, rotation);
                break;
            case 3:
                drawType3Road(group, cellWidth, cellHeight, rotation);
                break;
        }
    }

    /**
     * Draws a type 0 road tile.
     * @param group The group to which the road tile shape will be added.
     * @param cellWidth The width of a grid cell.
     * @param cellHeight The height of a grid cell.
     * @param rotation The rotation angle of the road tile.
     */
    public void drawType0Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Define road rectangle properties
        double roadWidth = cellWidth;
        double roadHeight = cellHeight - 10;
        double roadX = x * cellWidth;
        double roadY = y * cellHeight + 5;

        // Create and style the road rectangle
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));
        road.setRotate(rotation);

        // Add the road rectangle to the group
        group.getChildren().add(road);
    }

    /**
     * Draws a type 1 road tile.
     * @param group The group to which the road tile shape will be added.
     * @param cellWidth The width of a grid cell.
     * @param cellHeight The height of a grid cell.
     * @param rotation The rotation angle of the road tile.
     */
    public void drawType1Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Calculate arc properties based on grid cells
        // Additional adjustments are made based on rotation
        // Create and style the arc shape for the road tile
        // Create a circle shape to represent the junction
        // Combine the shapes in a group and apply rotation
        // Add the group to the main group
        double additionalX = 0;
        double additionalY = 0;
        double padding = 0.1; // %10 padding
        switch (rotation) {
            case 0:
                additionalX = -10;
                break;
            case 180:
                additionalY = -10;
                break;
            case 270:
                additionalX = -10;
                additionalY = -10;
                break;
        }
        double gridCellWidth = cellWidth / 2;
        double gridCellHeight = cellHeight / 2;
        double radius = (2 * gridCellWidth) * (1 - padding); // Arc radius spans four grid cells with padding
        double centerX = (x+1) * cellWidth + additionalX; // Center at grid cell center with additional padding
        double centerY = (y+1) * cellHeight + additionalY; // Center at grid cell center with additional padding

        // Determine arc starting and ending angles based on rotation
        double startAngle = 90, sweepAngle = 180;

        // Create Arc shape for the semi-transparent effect
        Arc arc1 = new Arc(centerX, centerY, radius, radius, startAngle, sweepAngle / 2); // Half sweep angle
        arc1.setType(ArcType.ROUND);
        arc1.setFill(Color.web("#FEFEFE"));

        // Create a Circle shape with the same dimensions as the Rectangle
        double circleRadius = 5; // Radius of the circle
        Circle circle = new Circle(centerX, centerY, circleRadius);
        circle.setFill(Color.web("#9BC6DF")); // Color of the circle

        // Combine shapes in a Group
        Group roadGroup = new Group(arc1, circle);

        // Rotate the entire group
        switch (rotation) {
            case 0:
                roadGroup.setRotate(90);
                break;
            case 90:
                roadGroup.setRotate(0);
                break;
            case 180:
                roadGroup.setRotate(270);
                break;
            case 270:
                roadGroup.setRotate(180);
                break;
        }

        group.getChildren().add(roadGroup);
    }

    /**
     * Draws a type 2 road tile.
     * @param group The group to which the road tile shape will be added.
     * @param cellWidth The width of a grid cell.
     * @param cellHeight The height of a grid cell.
     * @param rotation The rotation angle of the road tile.
     */
    public void drawType2Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Define road rectangle properties
        // Additional adjustments are made based on rotation
        // Create and style the road rectangles
        // Combine the rectangles in a group and apply rotation
        // Add the group to the main group
        Group roadGroup;
        double padding = 0.2;
        double roadWidth = cellWidth;
        double roadHeight = cellHeight - (cellHeight * padding);
        double roadX = x * cellWidth;
        double roadY = y * cellHeight + (cellHeight * padding/2);
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));

        double road2Width = cellWidth - (cellWidth * padding);
        double road2Height = cellHeight;
        double road2X = x * cellWidth + (cellWidth * padding/2);
        double road2Y = y * cellHeight;
        Rectangle road2 = new Rectangle(road2X, road2Y, road2Width, road2Height);
        road2.setFill(Color.web("#FEFEFE"));


        roadGroup = new Group(road, road2);
        roadGroup.setRotate(rotation);
        group.getChildren().add(roadGroup);

    }
    /**
     * Draws a type 3 road tile.
     * @param group The group to which the road tile shape will be added.
     * @param cellWidth The width of a grid cell.
     * @param cellHeight The height of a grid cell.
     * @param rotation The rotation angle of the road tile.
     */
    public void drawType3Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Calculate margins based on cell size
        // Define rectangle properties based on margins
        // Calculate base positions for rectangles
        // Create rectangles with calculated properties
        // Create a line based on rotation degree
        // Set line thickness and color
        // Adjust line position based on rotation
        // Combine rectangles and line in a group
        // Add the group to the main group
        double margin = cellWidth * 0.1; // 10% of cell width

        // Define rectangle properties based on margins
        double rect1Height = cellHeight - 2 * margin;

        // Calculate base positions for rectangles
        double baseX = x * cellWidth;
        double baseY = y * cellHeight;

        // Create rectangles with calculated properties
        Rectangle rect1 = new Rectangle(baseX, baseY + margin, cellWidth, rect1Height);
        rect1.setFill(Color.web("#FEFEFE"));

        // Add line based on rotation degree
        Line line = null;
        switch (rotation) {
            case 0:
                line = new Line(baseX, baseY + cellHeight - margin, baseX + cellWidth, baseY + cellHeight - margin);
                break;
            case 90:
                line = new Line(baseX + margin, baseY, baseX + margin, baseY + cellHeight);
                break;
            case 180:
                line = new Line(baseX, baseY + margin, baseX + cellWidth, baseY + margin);
                break;
            case 270:
                line = new Line(baseX + cellWidth - margin, baseY, baseX + cellWidth - margin, baseY + cellHeight);
                break;
            default:
                // Invalid rotation degree
                break;
        }

        // Set line thickness
        assert line != null;
        double lineLength = margin*1.8;
        double lineThickness = margin*1.8;
        line.setStrokeWidth(lineThickness);
        line.setStroke(Color.web("#FEFEFE"));
        if (rotation == 90 || rotation == 270) {
            line.setStartY(line.getStartY() + lineLength);
            line.setEndY(line.getEndY() - lineLength);
        } else {
            line.setStartX(line.getStartX() + lineLength);
            line.setEndX(line.getEndX() - lineLength);
        }

        rect1.setRotate(rotation);
        // Combine rectangles and line in a Group
        Group roadGroup = new Group(rect1, line);

        group.getChildren().add(roadGroup);
    }


    /**
     * Retrieves the type of the road tile.
     * @return The type of the road tile.
     */
    public int getType() {
        return type;
    }

    /**
     * Retrieves the rotation angle of the road tile.
     * @return The rotation angle of the road tile.
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Retrieves the x-coordinate of the road tile.
     * @return The x-coordinate of the road tile.
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the road tile.
     * @return The y-coordinate of the road tile.
     */
    public double getY() {
        return y;
    }
}