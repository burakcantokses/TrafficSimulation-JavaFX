package burak.tokses.ui.road;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

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
        }
    }

    public void drawType0Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Dikdörtgen çizimi
        double roadWidth = cellWidth;
        double roadHeight = cellHeight - 10; // Yukarıdan ve aşağıdan 5 piksel padding için
        double roadX = x * cellWidth;
        double roadY = y * cellHeight + 5; // Yukarıdan 5 piksel padding için
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));
        road.setRotate(rotation);
        group.getChildren().add(road);

    }

}
