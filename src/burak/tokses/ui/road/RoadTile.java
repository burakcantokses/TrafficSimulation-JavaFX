package burak.tokses.ui.road;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class RoadTile {
    public int type;
    public int rotation;
    public double x;
    public double y;

    public RoadTile(int type, int rotation, double x, double y) {
        this.type = type;
        this.rotation = rotation;
        this.x = x;
        this.y = y;
    }

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

    public void drawType0Road(Group group, double cellWidth, double cellHeight, int rotation) {
        double roadWidth = cellWidth;
        double roadHeight = cellHeight - 10;
        double roadX = x * cellWidth;
        double roadY = y * cellHeight + 5;
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));
        road.setRotate(rotation);
        group.getChildren().add(road);
    }

    public void drawType1Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Calculate arc properties based on grid cells
        double gridCellWidth = cellWidth / 2;
        double gridCellHeight = cellHeight / 2;
        double radius = 2 * gridCellWidth; // Arc radius spans four grid cells
        double centerX = x * cellWidth; // Center at grid cell center
        double centerY = y * cellHeight; // Center at grid cell center

        // Determine arc starting and ending angles based on rotation
        double startAngle, sweepAngle;
        switch (rotation) {
            case 0:
            case 90:
                startAngle = 90;
                sweepAngle = 180;
                break;
            case 180:
                startAngle = 90;
                sweepAngle = 180;
                break;
            case 270:
                startAngle = 90;
                sweepAngle = 180;
                break;
            default:
                throw new IllegalArgumentException("Invalid rotation angle for Type 1 road");
        }

        // Create two Arc shapes for the semi-transparent effect
        Arc arc1 = new Arc(centerX, centerY, radius, radius, startAngle, sweepAngle / 2); // Half sweep angle
        arc1.setType(ArcType.ROUND);
        arc1.setFill(Color.web("#FEFEFE"));


        // Combine shapes in a Group
        Group roadGroup = new Group(arc1);

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

        // Apply rotation to the entire group


        group.getChildren().add(roadGroup);
    }



    public void drawType2Road(Group group, double cellWidth, double cellHeight, int rotation) {
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

    public void drawType3Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Calculate margins
        double padding = 0.1 * cellWidth; // 10% of cell width

        // Rectangle 1: Full width, with top and bottom margins
        double rect1Width = cellWidth;
        double rect1Height = cellHeight - 2 * padding; // Subtract margins from height
        double rect1X = x * cellWidth;
        double rect1Y = y * cellHeight + padding; // Add top margin

        Rectangle rect1 = new Rectangle(rect1X, rect1Y, rect1Width, rect1Height);
        rect1.setFill(Color.web("#FEFEFE"));

        // Rectangle 2: Smaller width, with top, right, and left margins
        double rect2Width = cellWidth - 2 * padding;
        double rect2Height = cellHeight - padding; // Only top margin
        double rect2X = x * cellWidth + padding; // Add left margin
        double rect2Y = y * cellHeight; // No adjustment for default rotation
        if (rotation == 0) {
            rect2Y += padding;
        }
        Rectangle rect2 = new Rectangle(rect2X, rect2Y, rect2Width, rect2Height);
        rect2.setFill(Color.web("#FEFEFE"));


        // Add rectangles to the group
        Group roadGroup = new Group(rect1, rect2);

        if (rotation == 0) {
            rotation = 180;
        }
        if (rotation == 180) {
            rotation = 0;
        }

        // Apply rotation
        roadGroup.setRotate(rotation);

        group.getChildren().add(roadGroup);
    }




}
