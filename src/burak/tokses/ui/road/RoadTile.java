package burak.tokses.ui.road;

import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
            case 1:
                //drawType1Road(group, cellWidth, cellHeight, rotation);
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

    public void drawType1Road(Group group, double cellWidth, double cellHeight, int rotation) {
        // Yarım daireleri çizin
        double centerX = (x + 1) * cellWidth; // Hücrenin ortasından başlayacak şekilde X koordinatı
        double centerY = (y + 1) * cellHeight; // Hücrenin ortasından başlayacak şekilde Y koordinatı
        double radius = Math.min(cellWidth, cellHeight) / 2; // Hücre boyutunun minimumunu alarak yarıçapı belirleyin

        // Başlangıç açısını ve açı derecesini doğru bir şekilde ayarlayın
        double angleExtent = 90;

        Arc arc = new Arc(centerX, centerY, radius, radius, rotation, angleExtent);
        arc.setType(ArcType.OPEN);
        arc.setStroke(Color.web("#FEFEFE"));
        arc.setStrokeWidth(43);
        arc.setFill(null);
        group.getChildren().add(arc);
    }

    public void drawType2Road(Group group, double cellWidth, double cellHeight, int rotation) {
        //üstten ve alttan gridcell'in %10u kadar padding ver.
        Group roadGroup;
        double padding = 0.2;
        double roadWidth = cellWidth;
        double roadHeight = cellHeight - (cellHeight * padding);
        double roadX = x * cellWidth;
        double roadY = y * cellHeight + (cellHeight * padding/2);
        Rectangle road = new Rectangle(roadX, roadY, roadWidth, roadHeight);
        road.setFill(Color.web("#FEFEFE"));

        //kenardan ve yukarıdan gridcell'in %10u kadar padding ver.
        double road2Width = cellWidth - (cellWidth * padding);
        double road2Height = cellHeight;
        double road2X = x * cellWidth + (cellWidth * padding/2);
        double road2Y = y * cellHeight;
        Rectangle road2 = new Rectangle(road2X, road2Y, road2Width, road2Height);
        road2.setFill(Color.web("#FEFEFE"));


        //road ve road2'yi birleştir.
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
