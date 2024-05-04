package burak.tokses.ui.build;

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
    private int x;
    private int y;
    private int type;
    private int rotation;
    private int color;

    public Building(int x, int y, int type, int rotation, int color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.rotation = rotation;
        this.color = color;
    }

    public void print() {
        System.out.println("Building at x: " + x + ", y: " + y + ", type: " + type + ", rotation: " + rotation + ", color: " + color);
    }

}
