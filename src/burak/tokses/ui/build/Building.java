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

    public Node toNode() {
        // Assuming each grid cell is 50x50 pixels
        double cellSize = 53.33;

        switch (type) {
            case 0:
                return createType0Building(x, y, cellSize, rotation, color);
            case 1:
                return createType1Building(x, y, cellSize, rotation, color);
            case 2:
                return createType2Building(x, y, cellSize, color);
            default:
                return null;
        }
    }

    public Group createType0Building(int x, int y, double cellSize, int rotation, int color) {
        // Outer rectangle
        double outerWidth = cellSize * 2.3;
        double outerHeight = cellSize * 3.3;
        double outerX = x * cellSize - 5;
        double outerY = y * cellSize - 10;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);

        // Main rectangle
        double mainX = outerX + (outerWidth - cellSize * 2) / 2; // outerRectangle'ın ortasına yerleştirildi
        double mainY = outerY + 20; // outerRectangle'ın üst kısmına yerleştirildi ve biraz aşağı indirildi
        Rectangle mainRectangle = new Rectangle(mainX, mainY, cellSize * 2, cellSize * 2);
        mainRectangle.setFill(getColor(color));
        mainRectangle.setStroke(getStrokeColor(color));
        mainRectangle.setStrokeWidth(5);
        mainRectangle.setArcWidth(12);
        mainRectangle.setArcHeight(12);

        // Inner rectangle
        double innerSize = cellSize * 2 * 0.8;
        double innerX = mainX + (cellSize * 2 - innerSize) / 2;
        double innerY = mainY + (cellSize * 2 - innerSize) / 2;
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

    public Group createType1Building(int x, int y, double cellSize, int rotation, int color) {
        // Outer rectangle
        double outerWidth = cellSize * 2.3;
        double outerHeight = cellSize * 3.3;
        double outerX = x * cellSize - 5;
        double outerY = y * cellSize - 10;
        Rectangle outerRectangle = new Rectangle(outerX, outerY, outerWidth, outerHeight);
        outerRectangle.setArcWidth(12);
        outerRectangle.setArcHeight(12);
        outerRectangle.setFill(Color.web("#EFF9FE"));
        outerRectangle.setStroke(Color.BLACK);

        // Calculate main circle position
        double mainCircleX = (x + 1) * cellSize;
        double mainCircleY = (y + 1.1) * cellSize;

        // Main circle
        Circle mainCircle = new Circle(mainCircleX, mainCircleY, cellSize);
        mainCircle.setFill(getColor(color));
        mainCircle.setStroke(getStrokeColor(color));
        mainCircle.setStrokeWidth(5);

        // Calculate inner circle radius
        double innerRadius = cellSize * 0.85;

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


    public Rectangle createType2Building(int x, int y, double cellSize, int color) {
        // Create a rectangle
        Rectangle rectangle = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);

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



    /*
    //tüm şekilleri tek şekil halien gelip rotation verç.
    private Group createType0Building(int x, int y, double cellSize, int rotation, int color) {
        // Outer rectangle
        Rectangle outerRectangle = new Rectangle();
        switch (rotation) {
            case 90:
                outerRectangle = new Rectangle(x * cellSize - 5, y * cellSize - 10, cellSize * 3.5, cellSize * 2.5);
                break;
            case 180:
                outerRectangle = new Rectangle(x * cellSize - 10, y * cellSize - 10, cellSize * 2.5, cellSize * 3.5);
                break;
            case 270:
                outerRectangle = new Rectangle(x * cellSize - 10, y * cellSize - 5, cellSize * 3.5, cellSize * 2.5);
                break;
            default:
                outerRectangle = new Rectangle(x * cellSize - 10, y * cellSize - 5, cellSize * 2.5, cellSize * 3.5);
                break;
        }
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
    public Circle createType1Building(int x, int y, double cellSize, int color) {
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

    public Rectangle createBuildingRectangle(int x, int y, double cellSize, int rotation, int color) {
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
     */
}