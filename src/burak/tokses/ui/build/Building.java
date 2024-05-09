package burak.tokses.ui.build;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

import java.util.Arrays;
import java.util.List;

public class Building {
    private int type;
    private int rotation;
    private int color;
    private int x;
    private int y;

    private static final List<Color> COLORS = Arrays.asList(
            Color.web("#FECB9B"),
            Color.web("#90E4BE"),
            Color.web("#BCB5E9"),
            Color.web("#EF7E91"),
            Color.BLACK
    );

    private static final List<Color> STROKE_COLORS = Arrays.asList(
            Color.web("#F3B98D"),
            Color.web("#89CDB9"),
            Color.web("#AAA2D4"),
            Color.web("#DE7182"),
            Color.BLACK
    );

    public Building(int type, int rotation, int color, int x, int y) {
        this.type = type;
        this.rotation = rotation;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Node toNode(double cellWidth, double cellHeight) {
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
        int additionalX = rotation == 90 ? 3 : 0;
        int additionalY = rotation == 90 ? 1 : 0;
        double outerWidth = cellWidth * 2;
        double outerHeight = cellHeight * 3;
        double outerX = (x + additionalX) * cellWidth; // Subtract 1 from x before multiplying by cellWidth
        double outerY = (y - additionalY) * cellHeight;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);
        outerRectangle.setStrokeWidth(2);
        outerRectangle.setStrokeType(StrokeType.OUTSIDE);

        // Main rectangle
        double mainX = outerX + outerWidth / 1.75 - cellWidth; // outerRectangle'ın yatay merkezine göre hesaplandı
        double mainY = outerY + 10; // outerRectangle'ın üst kısmına yerleştirildi ve biraz aşağı indirildi
        Rectangle mainRectangle = new Rectangle(mainX, mainY, cellWidth * 1.7, cellHeight * 1.7);
        mainRectangle.setFill(getColor(color));
        mainRectangle.setStroke(getStrokeColor(color));
        mainRectangle.setStrokeType(StrokeType.CENTERED);
        mainRectangle.setStrokeWidth(5);
        mainRectangle.setArcWidth(12);
        mainRectangle.setArcHeight(12);

        // Inner rectangle
        double innerSize = cellWidth * 1.7 * 0.8;
        double innerX = mainX + (cellWidth * 1.7 - innerSize) / 2;
        double innerY = mainY + (cellHeight * 1.7 - innerSize) / 2;
        Rectangle innerRectangle = new Rectangle(innerX, innerY, innerSize, innerSize);
        innerRectangle.setFill(getColor(color));
        innerRectangle.setArcWidth(10);
        innerRectangle.setArcHeight(10);
        innerRectangle.setStroke(getStrokeColor(color));
        innerRectangle.setStrokeWidth(5);
        //rotatede kaymalar meydana geliyor.

        Group group = new Group(outerRectangle, mainRectangle, innerRectangle);

        switch (rotation) {
            case 90:
                group.getTransforms().add(new Rotate(270, outerX, outerY + outerHeight));
                break;
            case 270:
                group.getTransforms().add(new Rotate(90, outerX, outerY + outerHeight));
                break;
            default:
                group.setRotate(rotation);
                break;
        }
        return group;
    }

    public Group createType1Building(int x, int y, double cellWidth, double cellHeight, int rotation, int color) {
        // Outer rectangle
        int additionalX = rotation == 90 ? 3 : 0;
        int additionalY = rotation == 270 ? 3 : 0;
        double outerWidth = cellWidth * 2;
        double outerHeight = cellHeight * 3;
        double outerX = (x + additionalX) * cellWidth; // Subtract 1 from x before multiplying by cellWidth
        double outerY = (y - additionalY) * cellHeight;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);
        outerRectangle.setStrokeWidth(2);
        outerRectangle.setStrokeType(StrokeType.OUTSIDE);

        // Main circle
        double mainCircleX = outerX + outerWidth / 2; // Center of the outerRectangle
        double mainCircleY = outerY + outerHeight / 3; // 1/3 from the top of the outerRectangle
        double mainCircleRadius = outerWidth * 0.4; // 40% of the outerRectangle's width

        Circle mainCircle = new Circle(mainCircleX, mainCircleY, mainCircleRadius);
        mainCircle.setFill(getColor(color));
        mainCircle.setStroke(getStrokeColor(color));
        mainCircle.setStrokeWidth(5);

        // Inner circle
        double innerCircleRadius = mainCircleRadius * 0.8; // 80% of the mainCircle's radius
        Circle innerCircle = new Circle(mainCircleX, mainCircleY, innerCircleRadius);
        innerCircle.setFill(getColor(color));
        innerCircle.setStroke(getStrokeColor(color));
        innerCircle.setStrokeWidth(5);

        Group group = new Group(outerRectangle, mainCircle, innerCircle);
        switch (rotation) {
            case 90:
                group.getTransforms().add(new Rotate(270, outerX, outerY + outerHeight));
                break;
            case 270:
                group.getTransforms().add(new Rotate(90, outerX, outerY + outerHeight));
                break;
            default:
                group.setRotate(rotation);
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

    private Color getColor(int colorIndex) {
        return COLORS.get(colorIndex);
    }

    private Color getStrokeColor(int colorIndex) {
        return STROKE_COLORS.get(colorIndex);
    }

    public int getType() {
        return type;
    }

    public int getRotation() {
        return rotation;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}