package burak.tokses.ui.build;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

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

    public Node toNode(double cellWidth, double cellHeight) {
        // Assuming each grid cell is 50x50 pixels

        switch (type) {
            case 0:
                return createType0Building(x, y, cellWidth, cellHeight, rotation, color);
            case 1:
                return createType1Building(x, y, cellWidth, cellHeight, rotation, color);
            case 2:
                return createType2Building(x, y, cellWidth, cellHeight, color);
            default:
                return null;
        }
    }

    public Group createType0Building(int x, int y, double cellWidth, double cellHeight, int rotation, int color) {
        // Outer rectangle
        double outerWidth = cellWidth * 2.3;
        double outerHeight = cellHeight * 3.3;
        double outerX = x * cellWidth - 5;
        double outerY = y * cellHeight - 10;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);

        // Main rectangle
        double mainX = outerX + outerWidth / 2 - cellWidth; // outerRectangle'ın yatay merkezine göre hesaplandı
        double mainY = outerY + 20; // outerRectangle'ın üst kısmına yerleştirildi ve biraz aşağı indirildi
        Rectangle mainRectangle = new Rectangle(mainX, mainY, cellWidth * 2, cellHeight * 2);
        mainRectangle.setFill(getColor(color));
        mainRectangle.setStroke(getStrokeColor(color));
        mainRectangle.setStrokeWidth(5);
        mainRectangle.setArcWidth(12);
        mainRectangle.setArcHeight(12);

        // Inner rectangle
        double innerSize = cellWidth * 2 * 0.8;
        double innerX = mainX + (cellWidth * 2 - innerSize) / 2;
        double innerY = mainY + (cellHeight * 2 - innerSize) / 2;
        Rectangle innerRectangle = new Rectangle(innerX, innerY, innerSize, innerSize);
        innerRectangle.setFill(getColor(color));
        innerRectangle.setArcWidth(10);
        innerRectangle.setArcHeight(10);
        innerRectangle.setStroke(getStrokeColor(color));
        innerRectangle.setStrokeWidth(5);

        // Create a group to hold all rectangles
        Group group = new Group(outerRectangle, mainRectangle, innerRectangle);
        switch (rotation) {
            case 90:
                group.getTransforms().add(new Rotate(270, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
            case 180:
                group.getTransforms().add(new Rotate(180, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
            case 270:
                group.getTransforms().add(new Rotate(90, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
        }
        System.out.println("Building created at " + x + ", " + y + " with rotation " + rotation + " and color " + color);
        return group;
    }

    public Group createType1Building(int x, int y, double cellWidth, double cellHeight, int rotation, int color) {
        // Outer rectangle
        double outerWidth = cellWidth * 2.3;
        double outerHeight = cellHeight * 3.3;
        double outerX = x * cellWidth - 5;
        double outerY = y * cellHeight - 10;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);

        // Calculate main circle position
        double mainCircleX = (x + 1) * cellWidth;
        double mainCircleY = (y + 1.1) * cellHeight;

        // Main circle
        Circle mainCircle = new Circle(mainCircleX, mainCircleY, cellWidth);
        mainCircle.setFill(getColor(color));
        mainCircle.setStroke(getStrokeColor(color));
        mainCircle.setStrokeWidth(5);

        // Calculate inner circle radius
        double innerRadius = cellWidth * 0.85;

        // Inner circle
        Circle innerCircle = new Circle(mainCircleX, mainCircleY, innerRadius);
        innerCircle.setFill(getColor(color));
        innerCircle.setStroke(getStrokeColor(color));
        innerCircle.setStrokeWidth(5);

        // Create a group to hold all shapes
        Group group = new Group(outerRectangle, mainCircle, innerCircle);

        switch (rotation) {
            case 90:
                group.getTransforms().add(new Rotate(270, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
            case 180:
                group.getTransforms().add(new Rotate(180, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
            case 270:
                group.getTransforms().add(new Rotate(90, outerX + outerWidth / 2, outerY + outerHeight / 2));
                break;
        }
        return group;
    }

    public Rectangle createType2Building(int x, int y, double cellWidth, double cellHeight, int color) {
        // Create a rectangle
        Rectangle rectangle = new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);

        // Set the arc width and height to make the corners rounded
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        // Set the fill color based on the color index
        rectangle.setFill(getColor(color));
        rectangle.setStroke(getStrokeColor(color));
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(2);

        return rectangle;
    }

    public Color getColor(int colorIndex) {
        switch (colorIndex) {
            case 0:
                return Color.web("#FECB9B");
            case 1:
                return Color.web("#90E4BE");
            case 2:
                return Color.web("#BCB5E9");
            case 3:
                return Color.web("#EF7E91");
            default:
                return Color.BLACK;
        }
    }

    public Color getStrokeColor(int colorIndex) {
        switch (colorIndex) {
            case 0:
                return Color.web("#F3B98D");
            case 1:
                return Color.web("#89CDB9");
            case 2:
                return Color.web("#AAA2D4");
            case 3:
                return Color.web("#DE7182");
            default:
                return Color.BLACK;
        }
    }

}