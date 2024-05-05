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
    public static final double TILE_SIZE = 50; // Yol parçası boyutu

    public RoadTile(int type, int rotation, double x, double y) {
        this.type = type;
        this.rotation = rotation;
        this.x = x * TILE_SIZE; // Kare koordinatları piksel cinsine çevrildi.
        this.y = y * TILE_SIZE;
    }

    public void draw(Group group) {
        switch (type) {
            case 0:
                drawStraightRoad(group);
                break;
        }
    }

    private void drawStraightRoad(Group group) {
        double width = TILE_SIZE; // Yolun genişliği
        double height = TILE_SIZE * 0.8; // Yolun yüksekliği (1 gridcell'e yakın)

        Rectangle road = new Rectangle(x, y + (TILE_SIZE - height) / 2, width, height);
        road.setFill(Color.web("#FEFEFE"));
        group.getChildren().add(road);
    }

    private void drawTurnRoad(Group group) {
        Rectangle road = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
        road.setFill(Color.TRANSPARENT);
        road.setStroke(Color.BLACK);
        road.setStrokeWidth(3);
        group.getChildren().add(road);

        // Dönüşe göre çizim
        switch (rotation) {
            case 0:
                group.getChildren().add(new Line(x, y, x + TILE_SIZE, y + TILE_SIZE));
                break;
            case 90:
                group.getChildren().add(new Line(x + TILE_SIZE, y, x, y + TILE_SIZE));
                break;
            case 180:
                group.getChildren().add(new Line(x, y, x + TILE_SIZE, y + TILE_SIZE));
                break;
            case 270:
                group.getChildren().add(new Line(x, y, x + TILE_SIZE, y + TILE_SIZE));
                break;
        }
    }
}
