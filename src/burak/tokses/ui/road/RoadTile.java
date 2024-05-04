package burak.tokses.ui.road;

//There are four types of RoadTiles. These different road types are essential for creating city
//roads that can go in any direction and connect in any way. The main difference between road
//types are their shapes. Type with 0 index is a straight road and with index 1 is a curved road.
//Type 2 is a tile to put at the intersection of four different road whereas type 3 can be used at the
//intersection of three roads. RoadTile class takes the road type as an integer parameter and
//draw the road shape accordingly. It also takes rotation parameter too and draw the shapes con
//sidering the rotation as well. You can see the RoadTile types and their rotated versions. You
//also need to draw a centerline on the roads to distinguish left and right sides of the roads. That
//part is not visible on the images.
public class RoadTile {
    private int x;
    private int y;
    private int type;
    private int rotation;

    public RoadTile(int x, int y, int type, int rotation) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.rotation = rotation;
    }

    public void print() {
        System.out.println("RoadTile at x: " + x + ", y: " + y + ", type: " + type + ", rotation: " + rotation);
    }

}
