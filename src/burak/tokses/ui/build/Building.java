package burak.tokses.ui.build;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Building
//There are three types of buildings with four different colors. Type 0 and type 1 buildings are big
//ger buildings representing public buildings in the cities whereas type 3 is a smaller building rep
//resenting houses. There are also four different colors to make the buildings more diverse. Build
//ing class takes type, rotation and color as parameters to draw the buildings. As you will see in
//the level parsing section, input file includes color index for buildings. It means you need to have
//an array of four colors and you will give the color at given index to the building. You can define
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

        // Create a rectangle for the building
        Rectangle rectangle = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);

        // Set the fill color based on the color index
        switch (type) {
            case 0:
                rectangle.setFill(Color.RED);
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