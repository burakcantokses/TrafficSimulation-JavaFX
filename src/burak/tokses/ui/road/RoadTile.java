package burak.tokses.ui.road;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;
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

        // Calculate margins based on cell size
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





}
