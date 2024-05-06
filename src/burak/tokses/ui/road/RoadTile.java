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
            case 2:
                drawType2Road(group, cellWidth, cellHeight, rotation);
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

    public void drawType2Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Road'un dört kenarlarının koordinatlarını hesaplayın
        double roadWidth = cellWidth; // Sağdan ve soldan 5 piksel padding
        double roadHeight = cellHeight; // Yukarıdan ve aşağıdan 5 piksel padding
        double roadX = x * cellWidth; // Sağdan 5 piksel padding
        double roadY = y * cellHeight; // Yukarıdan 5 piksel padding

        // Road'u çizin
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));
        road.setRotate(rotation);
        group.getChildren().add(road);

        // Road'ın köşelerine kareler ekle
        double cornerSize = 5;
        Rectangle topLeftCorner = new Rectangle(roadX, roadY, cornerSize, cornerSize);
        topLeftCorner.setFill(Color.web("#9BC6DF"));
        topLeftCorner.setRotate(rotation);

        Rectangle topRightCorner = new Rectangle(roadX + roadWidth - cornerSize, roadY, cornerSize, cornerSize);
        topRightCorner.setFill(Color.web("#9BC6DF"));
        topRightCorner.setRotate(rotation);

        Rectangle bottomLeftCorner = new Rectangle(roadX, roadY + roadHeight - cornerSize, cornerSize, cornerSize);
        bottomLeftCorner.setFill(Color.web("#9BC6DF"));
        bottomLeftCorner.setRotate(rotation);

        Rectangle bottomRightCorner = new Rectangle(roadX + roadWidth - cornerSize, roadY + roadHeight - cornerSize, cornerSize, cornerSize);
        bottomRightCorner.setFill(Color.web("#9BC6DF"));
        bottomRightCorner.setRotate(rotation);

        group.getChildren().addAll(topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner);


    }



}
